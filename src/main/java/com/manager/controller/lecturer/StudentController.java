package com.manager.controller.lecturer;

import com.manager.DTO.*;
import com.manager.model.*;
import com.manager.repository.*;
import com.manager.service.EvaluationService;
import com.manager.service.ProjectGradeService;
import com.manager.service.ProjectService;
import com.manager.service.WeeklyRequirementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.*;

@Controller
public class StudentController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private WeeklyRequirementService weeklyRequirementService;
    @Autowired
    private WeeklyRequirementRepository weeklyRequirementRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ProjectRegistrationRepository projectRegistrationRepository;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private EvaluationService evaluationService;
    @Autowired
    private StudentWeeklyReportsRepository studentWeeklyReportsRepository;
    @Autowired
    private ProjectGradeRepository projectGradeRepository;
    @Autowired
    private ProjectGradeService projectGradeService;

    @GetMapping("/lecturer")
    public String getDashBoard(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
//        List<Order> set = orderRepository.findAll();
//        List<Order> list = new ArrayList<>();
//        for (Order i : set) {
//            if ((i.getOrderStatuses().size() == 2) ||
//                    (i.getOrderStatuses().size() == 3 && i.getShipper().equals(u))) {
//                for (OrderStatus j : i.getOrderStatuses()) {
//                    if (j.getOrderStatus().trim().contains("Đã xác nhận")) {
//                        list.add(i);
//                    }
//                }
//            }
//        }
//        double sum = set.stream().filter(order -> order.getOrderStatuses().size() == 4 && order.getShipper().equals(u))
//                .map(order -> order.getTotalPrice().doubleValue()).reduce(0.0, (aDouble, aDouble2) -> aDouble + aDouble2);
//        double count = set.stream().filter(order -> order.getOrderStatuses().size() == 4 && order.getShipper().equals(u)).count();
//        model.addAttribute("order_set", list);
//        model.addAttribute("sum", sum);
//        model.addAttribute("count", count);
        return "pages/lecturer/dashboard";
    }

    @GetMapping("/lecturer/list_StudentWeeklyReport")
    public String listWeeklyRequirements(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        List<WeeklyRequirement> requirements = weeklyRequirementService.getAllWeeklyRequirements(u.getId());
        model.addAttribute("weeklyRequirement", requirements);
        return "pages/lecturer/StudentWeeklyReport/StudentWeeklyReport";
    }

    @GetMapping("/lecturer/create_StudentWeeklyReport")
    public String getHistory(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        List<Project> project = projectRepository.findAllProject(u.getId());
        model.addAttribute("weeklyRequirement", new WeeklyRequirement());
        model.addAttribute("projects", project);
        return "pages/lecturer/StudentWeeklyReport/AddStudentWeeklyReport";
    }

    @GetMapping("/lecturer/edit_StudentWeeklyReport")
    public String editWeeklyReport(@RequestParam(name = "id") Optional<Long> id, Model model) {
        WeeklyRequirement p = null;
        if (id.isPresent()) {
            p = weeklyRequirementRepository.findById(id.get()).get();
        }
        model.addAttribute("weeklyRequirement", p);
        model.addAttribute("projects", projectService.getAllProjects());
        return "pages/lecturer/StudentWeeklyReport/EditStudentWeeklyReport";
    }

    @PostMapping("/lecturer/delete")
    public String deleteWeeklyRequirement(@RequestParam(name = "id") Long id, Model model) {
        weeklyRequirementService.deleteWeeklyRequirement(id);
        return "redirect:/lecturer/list_StudentWeeklyReport";
    }

    @PostMapping("/lecturer/create_StudentWeeklyReport")
    public String saveProject(@RequestParam(name = "requirementId") Optional<Long> requirementId,
                              @RequestParam(name = "project", defaultValue = "0") Optional<Long> project,
                              @RequestParam(name = "description") Optional<String> description,
                              @RequestParam(name = "startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> startDate,
                              @RequestParam(name = "endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Optional<Date> endDate
    ) {
        WeeklyRequirement w;
        if (!requirementId.isPresent()) {
            w = new WeeklyRequirement();
        } else {
            w = weeklyRequirementRepository.findById(requirementId.get()).get();
        }
        Project p = projectService.findProjectById(project.get());
        ProjectRegistration pr = projectRegistrationRepository.findAllProjectByUserID(p.getProjectId());
        w.setProject(p);
        w.setDescription(description.get());
        w.setStartDate(startDate.get());
        w.setEndDate(endDate.get());
        weeklyRequirementRepository.save(w);

        //add notification
        Notification notification = new Notification();
        notification.setUser(pr.getStudent());
        notification.setTitle("Đồ án " + p.getTitle() + " của bạn có lịch cho quá trình làm đồ án!!!");
        notification.setDescription("Đồ án " + p.getTitle() + " của bạn có lịch cho quá trình làm đồ án!!!");
        notificationRepository.save(notification);
        return "redirect:/lecturer/list_StudentWeeklyReport";
    }

    @GetMapping("/lecturer/evaluation")
    public String getEvaluation(@RequestParam(name = "id") Optional<Long> id, Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return "redirect:/login";
        }
        List<EvaluationDTO> evaluations = evaluationService.getAllWeeklyProject();
        model.addAttribute("evaluations", evaluations);
        return "pages/lecturer/Evaluations";
    }

//    @GetMapping("/download/{reportId}")
//    public ResponseEntity<Resource> downloadReport(@PathVariable Long reportId) {
//        try {
//            // Lấy thông tin file từ database
//            StudentWeeklyReport report = studentWeeklyReportsRepository.findById(reportId)
//                    .orElseThrow(() -> new RuntimeException("Report not found with ID: " + reportId));
//
//            String filePath = report.getReportFilePath(); // Đường dẫn tới file trong database
//
//            // Kết hợp đường dẫn và lấy file
//            Path file = fileStorageLocation.resolve(filePath).normalize();
//            Resource resource = new UrlResource(file.toUri());
//
//            if (resource.exists() && resource.isReadable()) {
//                return ResponseEntity.ok()
//                        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                        .body(resource);
//            } else {
//                throw new RuntimeException("File not found or not readable: " + filePath);
//            }
//        } catch (MalformedURLException ex) {
//            throw new RuntimeException("Error in file path", ex);
//        }
//    }

    @PostMapping("/lecturer/grade")
    public ResponseEntity<String> submitGrade(@RequestParam Long reportId,
                                              @RequestParam BigDecimal grade,
                                              @RequestParam String feedback,
                                              Authentication authentication) {
        // Lấy thông tin giảng viên từ SecurityContext
        String email = authentication.getName();
        User lecturer = userRepository.findByEmail(email);

        if (lecturer == null) {
            return ResponseEntity.badRequest().body("Giảng viên không tồn tại.");
        }

        // Tìm báo cáo sinh viên
        StudentWeeklyReport report = studentWeeklyReportsRepository.findById(reportId)
                .orElseThrow(() -> new RuntimeException("Báo cáo không tồn tại"));
        // Tạo mới hoặc cập nhật Evaluation
        Evaluation evaluation = new Evaluation();
        evaluation.setStudentReport(report);
        evaluation.setLecturer(lecturer);
        evaluation.setGrade(grade);
        evaluation.setFeedback(feedback);
        evaluation.setEvaluationDate(new Date());
        evaluationRepository.save(evaluation);

        Notification notification = new Notification();
        notification.setUser(report.getStudent());
        notification.setDescription("Bạn đã được Giảng viên " + lecturer.getFirstName() + lecturer.getLastName() + " chấm điểm cho báo cáo!!!");
        notification.setTitle("Chúc mừng bạn");
        notification.setDate(new Date());
        notificationRepository.save(notification);
        return ResponseEntity.ok("Chấm điểm thành công");
    }

    @GetMapping("/lecturer/projectGrade")
    public String getProjectGrade(@RequestParam(name = "id") Optional<Long> id, Model model, HttpServletRequest request) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return "redirect:/login";
        }
        List<ProjectGradeDTO> projectGrade = projectGradeService.getAllProjectGrade();
        model.addAttribute("projectGrades", projectGrade);
        return "pages/lecturer/ProjectGrade";
    }

    @PostMapping("/lecturer/projectGrades")
    @ResponseBody
    public String saveProjectGrade(@RequestParam Long projectId,
                                   @RequestParam BigDecimal finalGrade,
                                   @RequestParam String comments,
                                   Principal principal) {
        User lecturer = userRepository.findByEmail(principal.getName());
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        User student = projectGradeService.getAllProjectGradeStudent(project.getProjectId());

        ProjectGrade projectGrade = new ProjectGrade();
        projectGrade.setProject(project);
        projectGrade.setLecturer(lecturer);
        projectGrade.setStudent(student);
        projectGrade.setFinalGrade(finalGrade);
        projectGrade.setComments(comments);
        projectGrade.setGradeDate(new Date());
        projectGradeRepository.save(projectGrade);

        project.setStatus(Project.Status.HoanThanh);
        projectRepository.save(project);

        Notification notification = new Notification();
        notification.setUser(student);
        notification.setDescription("Bạn đã được Giảng viên " + lecturer.getFirstName() + lecturer.getLastName() + " chấm điểm cho đồ án của bạn!!!");
        notification.setTitle("Chúc mừng bạn đã hoàn thành đồ án");
        notification.setDate(new Date());
        notificationRepository.save(notification);

        return "Grade saved successfully!";
    }

    @GetMapping("/lecturer/confirm")
    public String getConfirm(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        List<ProjectConfirm> project = projectService.getAllProjectsConfirm(u.getId());
        model.addAttribute("weeklyRequirement", new WeeklyRequirement());
        model.addAttribute("projects", project);
        return "pages/lecturer/Confirm";
    }


    @PostMapping("/lecturer/regisConfirm")
    @ResponseBody
    public ResponseEntity<String> saveProjectConfirm(@RequestParam Long projectId) {
        Optional<Project> optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();

            // Kiểm tra trạng thái hiện tại trước khi cập nhật
            if (project.getStatus() == Project.Status.ChuaTienHanh) {
                project.setStatus(Project.Status.DangTienHanh);
                projectRepository.save(project);

                // Cập nhật trạng thái trong ProjectRegistration (nếu có)
                ProjectRegistration registration = projectRegistrationRepository.findAllProjectByUserID(project.getProjectId());

                registration.setStatus(ProjectRegistration.Status.XacNhan);
                projectRegistrationRepository.save(registration);
                Notification notification = new Notification();
                notification.setUser(registration.getStudent());
                notification.setDescription("Đồ án của bạn đã được xác nhận thành công!!!");
                notification.setTitle("Chúc mừng bạn đã đăng ký đồ án thành công");
                notification.setDate(new Date());
                notificationRepository.save(notification);

                return ResponseEntity.ok("Đồ án đã được xác nhận thành công!");
            } else {
                return ResponseEntity.badRequest().body("Trạng thái đồ án không phù hợp để xác nhận!");
            }
        }

        return ResponseEntity.badRequest().body("Không tìm thấy đồ án!");
    }

    @GetMapping("/lecturer/grade")
    public String getGrade(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User u = userRepository.findByEmail(email);
        if (u == null) {
            return "redirect:/login";
        }
        List<GradeDTO> projectGrade = projectGradeService.getAllGrade();
        model.addAttribute("projectGrades", projectGrade);
        return "pages/lecturer/Grade";
    }

    @GetMapping("/lecturer/details")
    public String showEvaluationDetails(@RequestParam("projectId") Long projectId,
                                        @RequestParam("userId") Long userId,
                                        Model model) {
        List<GradeDetailDTO> evaluations = evaluationService.evaluationList(projectId, userId);

        model.addAttribute("evaluations", evaluations);
        return "pages/lecturer/GradeDetail"; // Thymeleaf template name
    }
}
