-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: localhost:3306
-- Thời gian đã tạo: Th12 17, 2024 lúc 04:17 PM
-- Phiên bản máy phục vụ: 5.7.44
-- Phiên bản PHP: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `project_management`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `evaluations`
--

CREATE TABLE `evaluations` (
  `evaluation_id` bigint(20) NOT NULL,
  `evaluation_date` datetime(6) DEFAULT NULL,
  `feedback` varchar(255) DEFAULT NULL,
  `grade` decimal(19,2) DEFAULT NULL,
  `lecturer_id` bigint(20) NOT NULL,
  `student_report_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `hibernate_sequence`
--

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(25);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `notification`
--

CREATE TABLE `notification` (
  `id` bigint(20) NOT NULL,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `notification`
--

INSERT INTO `notification` (`id`, `date`, `description`, `title`, `user_id`) VALUES
(18, '2024-12-17 20:07:56.218000', 'Hãy bắt đầu cuộc hành trình thú vị của bạn tại nơi đây', 'Chào mừng bạn', 17),
(19, '2024-12-17 20:08:12.482000', 'Bạn đăng ký đồ án Xây dựng Website rút tiền tự đông thành công. Vui lòng chờ Giảng viên phê duyệt!', 'Chúc mừng bạn', 17),
(20, '2024-12-17 20:13:28.565000', 'Đồ án của bạn đã được xác nhận thành công!!!', 'Chúc mừng bạn đã đăng ký đồ án thành công', 17),
(21, NULL, 'Đồ án Xây dựng Website rút tiền tự đông của bạn có lịch cho quá trình làm đồ án!!!', 'Đồ án Xây dựng Website rút tiền tự đông của bạn có lịch cho quá trình làm đồ án!!!', 17),
(22, '2024-12-17 21:38:45.640000', 'Bạn đã gửi thành công báo cáo ngày Tue Dec 17 21:38:45 ICT 2024 !!!', 'Chúc mừng bạn', 17),
(23, '2024-12-17 21:47:33.396000', 'Bạn đã được Giảng viên tothai090801 chấm điểm cho báo cáo!!!', 'Chúc mừng bạn', 17),
(24, '2024-12-17 21:48:04.343000', 'Bạn đã được Giảng viên tothai090801 chấm điểm cho đồ án của bạn!!!', 'Chúc mừng bạn đã hoàn thành đồ án', 17);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `project`
--

CREATE TABLE `project` (
  `project_id` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `file` varchar(255) DEFAULT NULL,
  `max_students` int(11) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `project`
--

INSERT INTO `project` (`project_id`, `department`, `description`, `end_date`, `file`, `max_students`, `start_date`, `status`, `title`, `user_id`) VALUES
(1, 'CNTT', 'Phân tích và thiết kế Website bán đồ da', '2025-01-23', '/upload/serverjava.docx', 11, '2024-12-17', 'ChuaTienHanh', 'Phân tích và thiết kế Website bán đồ da', NULL),
(2, 'CNTT', 'Phân tích và thiết kế Website bán đồ da', '2025-01-31', '/upload/dareu-ek75-pro.rar', 10, '2024-12-17', 'ChuaTienHanh', 'Lập trình Website bán nội thất', NULL),
(3, 'CNTT', 'Xây dựng Website rút tiền tự đông', '2025-03-14', '/upload/JetBrains_ja-netfilter-all_Activation_Codes_Build_220801_Downloadly.ir.rar', 10, '2024-12-17', 'HoanThanh', 'Xây dựng Website rút tiền tự đông', NULL);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `project_grades`
--

CREATE TABLE `project_grades` (
  `project_grade_id` bigint(20) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `final_grade` decimal(19,2) DEFAULT NULL,
  `grade_date` datetime(6) DEFAULT NULL,
  `lecturer_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `project_lecturers`
--

CREATE TABLE `project_lecturers` (
  `assignment_id` bigint(20) NOT NULL,
  `assignment_date` datetime(6) DEFAULT NULL,
  `lecturer_id` bigint(20) NOT NULL,
  `project_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `project_registrations`
--

CREATE TABLE `project_registrations` (
  `registration_id` bigint(20) NOT NULL,
  `registration_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `role`
--

CREATE TABLE `role` (
  `role_id` bigint(20) NOT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `role`
--

INSERT INTO `role` (`role_id`, `role_name`) VALUES
(1, 'Admin'),
(2, 'Student'),
(3, 'Lecturer');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `student_weekly_reports`
--

CREATE TABLE `student_weekly_reports` (
  `student_report_id` bigint(20) NOT NULL,
  `comments` varchar(255) DEFAULT NULL,
  `report_file_path` varchar(255) DEFAULT NULL,
  `submission_date` datetime(6) DEFAULT NULL,
  `weekly_report_grade` double DEFAULT NULL,
  `requirement_id` bigint(20) NOT NULL,
  `student_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user`
--

CREATE TABLE `user` (
  `user_id` bigint(20) NOT NULL,
  `active` bit(1) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `class_name` varchar(255) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `dob` datetime(6) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` bit(1) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `major` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `otp_requested_time` datetime(6) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Đang đổ dữ liệu cho bảng `user`
--

INSERT INTO `user` (`user_id`, `active`, `address`, `class_name`, `code`, `department`, `dob`, `email`, `first_name`, `gender`, `image`, `last_name`, `major`, `otp`, `otp_requested_time`, `password`, `phone`, `username`, `role_id`) VALUES
(1, b'1', NULL, NULL, NULL, NULL, '2001-11-20 00:00:00.000000', 'liamnesson110201@gmail.com', 'Tô', b'1', NULL, 'Liam', NULL, '008228', '2024-12-16 03:24:55.389000', '$2a$10$AalcM7AAxxl1T4HrkChJJ.Uwd/4IYcLWMZAcSAhCk1C7OPyRshQ9m', '0352578346', NULL, 2),
(2, b'1', NULL, NULL, NULL, NULL, '2001-11-20 00:00:00.000000', 'jos1102@gmail.com', 'Tô', b'1', NULL, 'Liam', NULL, '008228', '2024-12-16 03:24:55.389000', '$2a$10$AalcM7AAxxl1T4HrkChJJ.Uwd/4IYcLWMZAcSAhCk1C7OPyRshQ9m', '0352578346', NULL, 2),
(17, b'1', NULL, NULL, NULL, NULL, NULL, 'marythaovan1102@gmail.com', 'marythaovan1102', b'0', NULL, '', NULL, '029723', '2024-12-17 20:07:04.877000', '$2a$10$k3GaiU22STyvLsO8yb//MujV5PiQqrbImr8iAuQI9.Xm5S7GeKg3a', NULL, NULL, 2),
(19, b'1', NULL, NULL, NULL, NULL, NULL, 'tothai090801@gmail.com', 'tothai090801', b'0', NULL, '', NULL, '632631', '2024-12-14 13:23:44.107000', '$2a$10$2lQEm1vp2Czw/xE7cu6q/.RqGBMT6v5KwlDEQYpzQpJPKwNFYbc6K', NULL, NULL, 3),
(20, b'1', NULL, NULL, NULL, NULL, NULL, 'tothai09@gmail.com', 'tothai09', b'0', NULL, '', NULL, '632631', '2024-12-14 13:23:44.107000', '$2a$10$2lQEm1vp2Czw/xE7cu6q/.RqGBMT6v5KwlDEQYpzQpJPKwNFYbc6K', NULL, NULL, 1),
(22, b'1', 'Mỹ Đình Hà Nội', NULL, NULL, NULL, '2001-04-03 00:00:00.000000', 'tothai01@gmail.com', 'Liam', b'1', NULL, 'Ykus', NULL, '632631', '2024-12-14 13:23:44.107000', '$2a$10$Ny87vW5r8suYublMHpThf.O4vikcMUE9/VGh.Y5DvzAOQ0pO4JpgG', '0352578346', NULL, 2),
(23, b'1', 'Mỹ Đình Hà Nội', NULL, NULL, NULL, '2001-08-09 00:00:00.000000', 'jostovanthai@gmail.com', 'Liam', b'1', NULL, 'Ykus', NULL, '632631', '2024-12-14 13:23:44.107000', '$2a$10$.G/Jg8p0q8Gs5LHanO3u9uEU2pWAV31ZFimzczRHC2q9GbvSc7tJ.', '0352578346', NULL, 3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `weekly_requirements`
--

CREATE TABLE `weekly_requirements` (
  `requirement_id` bigint(20) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `project_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `evaluations`
--
ALTER TABLE `evaluations`
  ADD PRIMARY KEY (`evaluation_id`),
  ADD KEY `FKp3vquyabvdtqwsdrmaxgvwkxe` (`lecturer_id`),
  ADD KEY `FKskchk0fjlr9vpy898jtfaxcw9` (`student_report_id`);

--
-- Chỉ mục cho bảng `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK48udxa7826ty0m0sgvpjlvsd8` (`user_id`);

--
-- Chỉ mục cho bảng `project`
--
ALTER TABLE `project`
  ADD PRIMARY KEY (`project_id`),
  ADD KEY `FKifg734fqd4embt197yij8n88o` (`user_id`);

--
-- Chỉ mục cho bảng `project_grades`
--
ALTER TABLE `project_grades`
  ADD PRIMARY KEY (`project_grade_id`),
  ADD KEY `FKet3unht4pexmv5v3jx7urffla` (`lecturer_id`),
  ADD KEY `FK8s3v66xhrs9apwp4cr6j0p8eo` (`project_id`),
  ADD KEY `FKg9ktj1cqui6beibxqglic8ohq` (`student_id`);

--
-- Chỉ mục cho bảng `project_lecturers`
--
ALTER TABLE `project_lecturers`
  ADD PRIMARY KEY (`assignment_id`),
  ADD KEY `FKiw6c0o6drxsditadr8xx0m0gm` (`lecturer_id`),
  ADD KEY `FKlgxg4udrjhp5jowvecvvrobnm` (`project_id`);

--
-- Chỉ mục cho bảng `project_registrations`
--
ALTER TABLE `project_registrations`
  ADD PRIMARY KEY (`registration_id`),
  ADD KEY `FKltiyysovodrj3jgeadstf1y2x` (`project_id`),
  ADD KEY `FK9glg538q7s1kuulqjps59c0a6` (`student_id`);

--
-- Chỉ mục cho bảng `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `student_weekly_reports`
--
ALTER TABLE `student_weekly_reports`
  ADD PRIMARY KEY (`student_report_id`),
  ADD KEY `FK63kinnvo6piwv7fnruwamr7o9` (`requirement_id`),
  ADD KEY `FK9ii7b2bnrmy153bi5rtl1udjv` (`student_id`);

--
-- Chỉ mục cho bảng `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`),
  ADD KEY `FK9usot4gododq1u90duvulb92d` (`role_id`);

--
-- Chỉ mục cho bảng `weekly_requirements`
--
ALTER TABLE `weekly_requirements`
  ADD PRIMARY KEY (`requirement_id`),
  ADD KEY `FKdwgkvnggoam20cpi8hq9ioirx` (`project_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `evaluations`
--
ALTER TABLE `evaluations`
  MODIFY `evaluation_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `project`
--
ALTER TABLE `project`
  MODIFY `project_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `project_grades`
--
ALTER TABLE `project_grades`
  MODIFY `project_grade_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `project_lecturers`
--
ALTER TABLE `project_lecturers`
  MODIFY `assignment_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `project_registrations`
--
ALTER TABLE `project_registrations`
  MODIFY `registration_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT cho bảng `student_weekly_reports`
--
ALTER TABLE `student_weekly_reports`
  MODIFY `student_report_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT cho bảng `weekly_requirements`
--
ALTER TABLE `weekly_requirements`
  MODIFY `requirement_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `evaluations`
--
ALTER TABLE `evaluations`
  ADD CONSTRAINT `FKp3vquyabvdtqwsdrmaxgvwkxe` FOREIGN KEY (`lecturer_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKskchk0fjlr9vpy898jtfaxcw9` FOREIGN KEY (`student_report_id`) REFERENCES `student_weekly_reports` (`student_report_id`);

--
-- Các ràng buộc cho bảng `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `FK48udxa7826ty0m0sgvpjlvsd8` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `project`
--
ALTER TABLE `project`
  ADD CONSTRAINT `FKifg734fqd4embt197yij8n88o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `project_grades`
--
ALTER TABLE `project_grades`
  ADD CONSTRAINT `FK8s3v66xhrs9apwp4cr6j0p8eo` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`),
  ADD CONSTRAINT `FKet3unht4pexmv5v3jx7urffla` FOREIGN KEY (`lecturer_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKg9ktj1cqui6beibxqglic8ohq` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `project_lecturers`
--
ALTER TABLE `project_lecturers`
  ADD CONSTRAINT `FKiw6c0o6drxsditadr8xx0m0gm` FOREIGN KEY (`lecturer_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKlgxg4udrjhp5jowvecvvrobnm` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);

--
-- Các ràng buộc cho bảng `project_registrations`
--
ALTER TABLE `project_registrations`
  ADD CONSTRAINT `FK9glg538q7s1kuulqjps59c0a6` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`),
  ADD CONSTRAINT `FKltiyysovodrj3jgeadstf1y2x` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);

--
-- Các ràng buộc cho bảng `student_weekly_reports`
--
ALTER TABLE `student_weekly_reports`
  ADD CONSTRAINT `FK63kinnvo6piwv7fnruwamr7o9` FOREIGN KEY (`requirement_id`) REFERENCES `weekly_requirements` (`requirement_id`),
  ADD CONSTRAINT `FK9ii7b2bnrmy153bi5rtl1udjv` FOREIGN KEY (`student_id`) REFERENCES `user` (`user_id`);

--
-- Các ràng buộc cho bảng `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK9usot4gododq1u90duvulb92d` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`);

--
-- Các ràng buộc cho bảng `weekly_requirements`
--
ALTER TABLE `weekly_requirements`
  ADD CONSTRAINT `FKdwgkvnggoam20cpi8hq9ioirx` FOREIGN KEY (`project_id`) REFERENCES `project` (`project_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
