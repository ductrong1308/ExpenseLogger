package com.example.expenselogger.db;

public class SeedData {
    public static final String SQL_INSERT_USERS =
            "INSERT INTO Users(id, firstName, lastName, emailAddress, password) " +
                    "VALUES (1, 'System', 'Admin', 'admin@gmail.com', '123')";

    public static  final String SQL_INSERT_CATEGORIES =
            "INSERT INTO Categories (id, categoryName, userId) VALUES (null, 'Meals', 1)," +
            " (null, 'Education', 1), (null, 'Entertainment', 1), (null, 'Transportation', 1);";

    public static final String SQL_INSERT_SETTINGS =
            "INSERT INTO Settings (id, name, value, userId) VALUES (null, 'Currency', 'CAD', 1)";

    public  static final String SQL_INSERT_EXPENSES =
            "insert into Expenses (id, createdDate, category, amount, userId) values " +
                    "(null, '2020-02-07 18:58:48', 'Meals', 99.63, 1)" +
                    ", (null, '2020-02-14 16:54:02', 'Transportation', 188.75, 1)" +
                    ", (null, '2020-01-25 19:32:28', 'Education', 902.96, 1)" +
                    ", (null, '2020-01-19 15:51:49', 'Education', 969.82, 1)" +
                    ", (null, '2020-03-20 21:19:40', 'Meals', 925.75, 1)" +
                    ", (null, '2020-01-20 22:02:13', 'Meals', 818.1, 1)" +
                    ", (null, '2020-03-14 20:03:05', 'Education', 161.99, 1)" +
                    ", (null, '2020-02-29 10:23:54', 'Transportation', 290.41, 1)" +
                    ", (null, '2020-01-12 15:32:08', 'Meals', 617.14, 1)" +
                    ", (null, '2020-01-12 08:30:14', 'Education', 536.68, 1)" +
                    ", (null, '2020-01-17 00:04:01', 'Education', 277.69, 1)" +
                    ", (null, '2020-03-21 17:03:26', 'Meals', 56.15, 1)" +
                    ", (null, '2020-03-04 07:45:31', 'Education', 570.37, 1)" +
                    ", (null, '2020-02-18 15:14:48', 'Meals', 53.61, 1)" +
                    ", (null, '2020-01-07 15:59:48', 'Education', 400.82, 1)" +
                    ", (null, '2020-03-23 18:18:00', 'Entertainment', 21.43, 1)" +
                    ", (null, '2020-02-25 16:04:45', 'Entertainment', 204.88, 1)" +
                    ", (null, '2020-03-21 04:43:16', 'Education', 505.64, 1)" +
                    ", (null, '2020-02-07 02:07:05', 'Education', 231.05, 1)" +
                    ", (null, '2020-02-22 10:18:02', 'Entertainment', 348.0, 1)" +
                    ", (null, '2020-02-25 07:27:58', 'Transportation', 824.44, 1)" +
                    ", (null, '2020-02-08 10:28:51', 'Education', 168.16, 1)" +
                    ", (null, '2020-01-08 07:40:43', 'Education', 610.9, 1)" +
                    ", (null, '2020-02-22 05:25:00', 'Entertainment', 186.55, 1)" +
                    ", (null, '2020-02-05 06:33:06', 'Education', 874.44, 1)" +
                    ", (null, '2020-02-17 16:26:25', 'Education', 862.98, 1)" +
                    ", (null, '2020-02-19 02:44:05', 'Meals', 694.97, 1)" +
                    ", (null, '2020-01-02 18:37:36', 'Education', 531.82, 1)" +
                    ", (null, '2020-03-22 21:34:31', 'Transportation', 287.2, 1)" +
                    ", (null, '2020-03-14 15:56:04', 'Transportation', 425.44, 1)" +
                    ", (null, '2020-02-18 03:29:22', 'Meals', 588.78, 1)" +
                    ", (null, '2020-03-12 07:36:16', 'Entertainment', 531.03, 1)" +
                    ", (null, '2020-01-08 14:05:59', 'Education', 839.15, 1)" +
                    ", (null, '2020-02-05 02:49:06', 'Education', 303.08, 1)" +
                    ", (null, '2020-01-08 03:21:32', 'Entertainment', 93.09, 1)" +
                    ", (null, '2020-01-14 21:11:31', 'Education', 268.94, 1)" +
                    ", (null, '2020-03-04 22:36:11', 'Meals', 92.9, 1)" +
                    ", (null, '2020-03-20 04:53:22', 'Meals', 829.48, 1)" +
                    ", (null, '2020-01-30 15:40:43', 'Transportation', 232.43, 1)" +
                    ", (null, '2020-02-08 22:47:41', 'Meals', 349.35, 1)" +
                    ", (null, '2020-01-16 05:58:03', 'Education', 880.25, 1)" +
                    ", (null, '2020-02-02 04:12:10', 'Education', 60.96, 1)" +
                    ", (null, '2020-03-29 09:14:15', 'Education', 326.96, 1)" +
                    ", (null, '2020-01-23 10:42:40', 'Education', 920.16, 1)" +
                    ", (null, '2020-03-12 16:31:26', 'Entertainment', 315.94, 1)" +
                    ", (null, '2020-02-06 18:14:19', 'Transportation', 58.55, 1)" +
                    ", (null, '2020-02-19 14:04:07', 'Entertainment', 639.92, 1)" +
                    ", (null, '2020-03-08 13:41:21', 'Education', 495.86, 1)" +
                    ", (null, '2020-01-04 23:16:29', 'Entertainment', 54.21, 1)" +
                    ", (null, '2020-01-02 03:02:30', 'Meals', 13.75, 1)" +
                    ", (null, '2020-03-30 22:56:39', 'Meals', 501.4, 1)" +
                    ", (null, '2020-02-16 13:37:07', 'Meals', 759.44, 1)" +
                    ", (null, '2020-01-19 23:34:52', 'Education', 799.03, 1)" +
                    ", (null, '2020-03-14 08:29:45', 'Entertainment', 783.24, 1)" +
                    ", (null, '2020-02-23 20:11:19', 'Meals', 779.7, 1)" +
                    ", (null, '2020-01-16 12:16:28', 'Education', 582.72, 1)" +
                    ", (null, '2020-03-20 08:43:04', 'Meals', 614.0, 1)" +
                    ", (null, '2020-02-21 14:47:17', 'Transportation', 286.83, 1)" +
                    ", (null, '2020-02-26 14:18:07', 'Transportation', 152.46, 1)" +
                    ", (null, '2020-03-27 01:18:16', 'Meals', 223.51, 1)" +
                    ", (null, '2020-03-26 02:09:28', 'Entertainment', 195.45, 1)" +
                    ", (null, '2020-01-26 11:08:29', 'Entertainment', 966.47, 1)" +
                    ", (null, '2020-01-20 06:31:48', 'Education', 25.87, 1)" +
                    ", (null, '2020-03-05 05:56:07', 'Meals', 463.59, 1)" +
                    ", (null, '2020-03-05 13:50:02', 'Transportation', 946.26, 1)" +
                    ", (null, '2020-03-18 06:12:37', 'Meals', 228.24, 1)" +
                    ", (null, '2020-02-01 02:47:21', 'Education', 840.25, 1)" +
                    ", (null, '2020-03-16 07:37:21', 'Meals', 193.91, 1)" +
                    ", (null, '2020-03-05 03:58:27', 'Meals', 967.65, 1)" +
                    ", (null, '2020-03-22 08:07:27', 'Meals', 6.53, 1)" +
                    ", (null, '2020-03-26 23:05:32', 'Meals', 741.82, 1)" +
                    ", (null, '2020-01-03 05:56:15', 'Transportation', 331.78, 1)" +
                    ", (null, '2020-03-26 00:14:21', 'Education', 248.68, 1)" +
                    ", (null, '2020-03-25 05:31:59', 'Education', 255.85, 1)" +
                    ", (null, '2020-03-28 00:22:52', 'Meals', 388.23, 1)" +
                    ", (null, '2020-03-06 08:36:16', 'Transportation', 99.19, 1)" +
                    ", (null, '2020-02-29 13:02:37', 'Entertainment', 663.8, 1)" +
                    ", (null, '2020-01-20 12:45:09', 'Entertainment', 125.55, 1)" +
                    ", (null, '2020-02-14 09:03:02', 'Entertainment', 825.19, 1)" +
                    ", (null, '2020-03-15 00:24:23', 'Education', 239.56, 1)" +
                    ", (null, '2020-02-19 16:52:30', 'Entertainment', 781.11, 1)" +
                    ", (null, '2020-02-21 10:18:15', 'Transportation', 588.3, 1)" +
                    ", (null, '2020-03-20 12:20:39', 'Education', 232.07, 1)" +
                    ", (null, '2020-03-02 23:09:44', 'Transportation', 819.72, 1)" +
                    ", (null, '2020-02-25 13:04:09', 'Entertainment', 595.19, 1)" +
                    ", (null, '2020-01-03 09:56:29', 'Education', 554.62, 1)" +
                    ", (null, '2020-01-31 00:36:59', 'Education', 210.12, 1)" +
                    ", (null, '2020-03-04 06:58:50', 'Entertainment', 963.17, 1)" +
                    ", (null, '2020-02-05 18:05:25', 'Meals', 114.72, 1)" +
                    ", (null, '2020-02-10 05:16:13', 'Transportation', 866.51, 1)" +
                    ", (null, '2020-03-15 16:09:48', 'Meals', 415.81, 1)" +
                    ", (null, '2020-03-30 06:28:55', 'Entertainment', 792.39, 1)" +
                    ", (null, '2020-02-15 17:50:32', 'Education', 998.83, 1)" +
                    ", (null, '2020-01-28 09:03:51', 'Transportation', 680.6, 1)" +
                    ", (null, '2020-02-25 00:56:13', 'Entertainment', 795.65, 1)" +
                    ", (null, '2020-01-30 05:19:36', 'Entertainment', 553.93, 1)" +
                    ", (null, '2020-02-10 00:22:05', 'Meals', 210.4, 1)" +
                    ", (null, '2020-03-12 16:35:54', 'Transportation', 98.37, 1)" +
                    ", (null, '2020-03-03 04:38:16', 'Transportation', 595.5, 1)" +
                    ", (null, '2020-03-10 03:13:37', 'Education', 839.79, 1)" +
                    ", (null, '2020-01-23 06:49:31', 'Transportation', 272.8, 1)" +
                    ", (null, '2020-01-26 02:30:44', 'Meals', 282.11, 1)" +
                    ", (null, '2020-01-06 09:12:26', 'Entertainment', 147.02, 1)" +
                    ", (null, '2020-02-03 23:22:23', 'Transportation', 53.7, 1)" +
                    ", (null, '2020-01-30 06:21:23', 'Meals', 676.03, 1)" +
                    ", (null, '2020-01-26 17:04:09', 'Entertainment', 98.09, 1)" +
                    ", (null, '2020-03-24 03:41:41', 'Meals', 12.0, 1)" +
                    ", (null, '2020-01-08 04:45:51', 'Meals', 65.0, 1)" +
                    ", (null, '2020-01-29 18:50:51', 'Transportation', 740.84, 1)" +
                    ", (null, '2020-01-07 03:51:30', 'Education', 485.67, 1)" +
                    ", (null, '2020-01-09 04:57:20', 'Entertainment', 639.11, 1)" +
                    ", (null, '2020-03-24 14:44:39', 'Meals', 289.83, 1)" +
                    ", (null, '2020-02-29 06:04:58', 'Entertainment', 542.02, 1)" +
                    ", (null, '2020-03-28 17:46:02', 'Meals', 345.66, 1)" +
                    ", (null, '2020-01-25 23:34:10', 'Transportation', 232.95, 1)" +
                    ", (null, '2020-01-18 13:11:57', 'Transportation', 328.24, 1)" +
                    ", (null, '2020-03-23 18:57:32', 'Entertainment', 481.04, 1)" +
                    ", (null, '2020-01-12 22:21:36', 'Meals', 370.55, 1)" +
                    ", (null, '2020-01-26 12:16:43', 'Entertainment', 940.99, 1)" +
                    ", (null, '2020-02-05 21:03:50', 'Meals', 884.65, 1)" +
                    ", (null, '2020-01-11 04:23:02', 'Entertainment', 324.16, 1)" +
                    ", (null, '2020-03-04 00:51:47', 'Education', 380.29, 1)" +
                    ", (null, '2020-02-09 03:44:56', 'Meals', 549.47, 1)" +
                    ", (null, '2020-01-03 18:45:35', 'Entertainment', 716.95, 1)" +
                    ", (null, '2020-02-16 21:48:58', 'Transportation', 368.98, 1)" +
                    ", (null, '2020-02-15 07:12:36', 'Education', 165.77, 1)" +
                    ", (null, '2020-03-09 12:17:44', 'Entertainment', 950.33, 1)" +
                    ", (null, '2020-01-29 03:40:18', 'Entertainment', 206.47, 1)" +
                    ", (null, '2020-01-11 13:42:51', 'Entertainment', 273.44, 1)" +
                    ", (null, '2020-02-22 22:39:36', 'Entertainment', 116.58, 1)" +
                    ", (null, '2020-01-09 13:20:21', 'Education', 590.01, 1)" +
                    ", (null, '2020-01-26 08:00:47', 'Education', 928.98, 1)" +
                    ", (null, '2020-02-17 11:35:43', 'Transportation', 929.24, 1)" +
                    ", (null, '2020-02-11 15:03:38', 'Meals', 837.61, 1)" +
                    ", (null, '2020-03-20 20:48:49', 'Entertainment', 821.66, 1)" +
                    ", (null, '2020-03-18 16:37:59', 'Meals', 514.74, 1)" +
                    ", (null, '2020-02-14 19:43:41', 'Transportation', 69.23, 1)" +
                    ", (null, '2020-03-22 13:52:53', 'Education', 60.97, 1)" +
                    ", (null, '2020-01-09 06:11:36', 'Education', 23.12, 1)" +
                    ", (null, '2020-02-19 22:02:12', 'Transportation', 775.24, 1)" +
                    ", (null, '2020-02-20 11:02:24', 'Education', 909.54, 1)" +
                    ", (null, '2020-01-12 20:11:30', 'Transportation', 311.34, 1)" +
                    ", (null, '2020-01-05 12:10:55', 'Education', 584.56, 1)" +
                    ", (null, '2020-03-30 10:25:09', 'Entertainment', 422.22, 1)" +
                    ", (null, '2020-03-14 01:33:09', 'Transportation', 573.82, 1)" +
                    ", (null, '2020-02-25 19:56:01', 'Entertainment', 304.06, 1)" +
                    ", (null, '2020-03-22 05:01:39', 'Meals', 545.09, 1)" +
                    ", (null, '2020-03-31 12:08:53', 'Entertainment', 860.42, 1)" +
                    ", (null, '2020-03-22 09:18:02', 'Entertainment', 794.65, 1)" +
                    ", (null, '2020-01-08 19:34:24', 'Entertainment', 747.68, 1)" +
                    ", (null, '2020-01-01 13:13:17', 'Meals', 968.59, 1)" +
                    ", (null, '2020-02-13 02:27:21', 'Entertainment', 713.19, 1)" +
                    ", (null, '2020-01-04 22:45:26', 'Entertainment', 775.09, 1)" +
                    ", (null, '2020-03-21 01:29:51', 'Education', 746.42, 1)" +
                    ", (null, '2020-02-15 16:04:37', 'Meals', 241.14, 1)" +
                    ", (null, '2020-03-21 07:36:54', 'Meals', 200.32, 1)" +
                    ", (null, '2020-03-26 11:56:16', 'Entertainment', 286.0, 1)" +
                    ", (null, '2020-03-14 02:08:03', 'Transportation', 544.27, 1)" +
                    ", (null, '2020-01-02 06:10:43', 'Transportation', 373.13, 1)" +
                    ", (null, '2020-02-17 21:38:43', 'Meals', 85.81, 1)" +
                    ", (null, '2020-01-08 08:32:28', 'Education', 906.55, 1)" +
                    ", (null, '2020-01-03 08:41:47', 'Entertainment', 262.48, 1)" +
                    ", (null, '2020-03-18 23:35:23', 'Education', 20.0, 1)" +
                    ", (null, '2020-01-29 07:08:16', 'Meals', 542.83, 1)" +
                    ", (null, '2020-02-18 09:08:23', 'Entertainment', 598.14, 1)" +
                    ", (null, '2020-01-13 12:38:19', 'Entertainment', 398.12, 1)" +
                    ", (null, '2020-02-04 02:15:16', 'Transportation', 727.05, 1)" +
                    ", (null, '2020-02-26 12:07:03', 'Meals', 449.5, 1)" +
                    ", (null, '2020-02-17 23:17:37', 'Entertainment', 53.98, 1)" +
                    ", (null, '2020-02-11 14:17:03', 'Entertainment', 115.49, 1)" +
                    ", (null, '2020-01-02 16:38:35', 'Transportation', 521.19, 1)" +
                    ", (null, '2020-01-22 02:01:48', 'Transportation', 819.42, 1)" +
                    ", (null, '2020-01-01 09:53:20', 'Entertainment', 311.52, 1)" +
                    ", (null, '2020-03-30 00:53:25', 'Education', 372.43, 1)" +
                    ", (null, '2020-02-12 09:57:50', 'Meals', 219.16, 1)" +
                    ", (null, '2020-03-08 22:08:46', 'Education', 228.27, 1)" +
                    ", (null, '2020-02-16 17:45:37', 'Education', 487.12, 1)" +
                    ", (null, '2020-02-09 10:51:55', 'Meals', 559.55, 1)" +
                    ", (null, '2020-03-02 23:42:42', 'Meals', 552.56, 1)" +
                    ", (null, '2020-02-12 03:06:39', 'Transportation', 977.45, 1)" +
                    ", (null, '2020-01-10 00:11:16', 'Meals', 333.78, 1)" +
                    ", (null, '2020-01-16 10:17:27', 'Transportation', 601.02, 1)" +
                    ", (null, '2020-01-23 17:38:30', 'Education', 218.95, 1)" +
                    ", (null, '2020-02-10 16:34:42', 'Transportation', 46.28, 1)" +
                    ", (null, '2020-03-13 17:03:56', 'Meals', 313.67, 1)" +
                    ", (null, '2020-03-17 17:55:58', 'Meals', 401.78, 1)" +
                    ", (null, '2020-01-01 19:19:28', 'Meals', 248.25, 1)" +
                    ", (null, '2020-03-12 16:16:08', 'Meals', 760.54, 1)" +
                    ", (null, '2020-02-29 03:09:51', 'Entertainment', 463.59, 1)" +
                    ", (null, '2020-02-06 10:29:15', 'Entertainment', 227.5, 1)" +
                    ", (null, '2020-02-05 19:21:47', 'Entertainment', 727.28, 1)" +
                    ", (null, '2020-03-19 23:47:00', 'Education', 141.74, 1)" +
                    ", (null, '2020-01-26 21:32:34', 'Education', 970.49, 1)" +
                    ", (null, '2020-02-20 21:04:11', 'Entertainment', 942.77, 1)" +
                    ", (null, '2020-01-09 01:02:16', 'Meals', 439.18, 1)" +
                    ", (null, '2020-03-16 19:22:36', 'Education', 875.35, 1)" +
                    ", (null, '2020-03-15 22:23:11', 'Meals', 222.69, 1)" +
                    ", (null, '2020-03-27 09:28:57', 'Transportation', 533.23, 1)" +
                    ", (null, '2020-01-12 14:12:47', 'Meals', 303.21, 1)" +
                    ", (null, '2020-02-23 17:37:01', 'Education', 944.52, 1)" +
                    ", (null, '2020-02-22 22:32:19', 'Education', 176.23, 1)" +
                    ", (null, '2020-03-02 13:59:11', 'Transportation', 780.7, 1)" +
                    ", (null, '2020-02-13 15:09:37', 'Meals', 919.89, 1)" +
                    ", (null, '2020-01-16 23:16:16', 'Entertainment', 497.44, 1)" +
                    ", (null, '2020-02-27 08:51:53', 'Entertainment', 960.63, 1)" +
                    ", (null, '2020-03-04 23:56:41', 'Transportation', 8.52, 1)" +
                    ", (null, '2020-01-07 18:49:36', 'Education', 950.58, 1)" +
                    ", (null, '2020-02-10 08:31:54', 'Transportation', 485.9, 1)" +
                    ", (null, '2020-01-16 19:06:42', 'Entertainment', 615.78, 1)" +
                    ", (null, '2020-02-13 03:44:13', 'Meals', 686.02, 1)" +
                    ", (null, '2020-02-25 21:28:26', 'Education', 927.28, 1)" +
                    ", (null, '2020-01-19 11:50:41', 'Transportation', 814.94, 1)" +
                    ", (null, '2020-03-16 16:15:29', 'Education', 13.96, 1)" +
                    ", (null, '2020-01-19 15:26:22', 'Entertainment', 463.74, 1)" +
                    ", (null, '2020-03-14 07:26:52', 'Entertainment', 961.86, 1)" +
                    ", (null, '2020-01-01 01:25:20', 'Meals', 452.26, 1)" +
                    ", (null, '2020-02-21 04:10:18', 'Meals', 412.2, 1)" +
                    ", (null, '2020-02-25 18:54:03', 'Transportation', 309.01, 1)" +
                    ", (null, '2020-03-06 21:54:42', 'Entertainment', 124.32, 1)" +
                    ", (null, '2020-02-11 21:00:19', 'Transportation', 254.81, 1)" +
                    ", (null, '2020-01-22 19:38:15', 'Meals', 669.23, 1)" +
                    ", (null, '2020-03-12 01:01:47', 'Entertainment', 619.88, 1)" +
                    ", (null, '2020-02-02 08:33:06', 'Education', 823.27, 1)" +
                    ", (null, '2020-01-12 16:55:35', 'Transportation', 360.6, 1)" +
                    ", (null, '2020-02-07 00:55:43', 'Education', 506.41, 1)" +
                    ", (null, '2020-01-20 18:24:34', 'Education', 748.78, 1)" +
                    ", (null, '2020-02-19 17:44:13', 'Entertainment', 649.31, 1)" +
                    ", (null, '2020-03-13 20:25:52', 'Meals', 71.9, 1)" +
                    ", (null, '2020-01-21 18:05:59', 'Transportation', 945.06, 1)" +
                    ", (null, '2020-03-27 17:52:06', 'Meals', 257.12, 1)" +
                    ", (null, '2020-02-14 16:05:07', 'Entertainment', 343.6, 1)" +
                    ", (null, '2020-01-25 00:42:13', 'Entertainment', 182.44, 1)" +
                    ", (null, '2020-01-18 12:46:10', 'Transportation', 690.2, 1)" +
                    ", (null, '2020-01-20 03:23:47', 'Education', 163.36, 1)" +
                    ", (null, '2020-03-01 08:31:46', 'Education', 242.88, 1)" +
                    ", (null, '2020-03-23 16:21:59', 'Education', 561.06, 1)" +
                    ", (null, '2020-03-13 06:13:53', 'Education', 739.59, 1)" +
                    ", (null, '2020-01-08 16:35:47', 'Entertainment', 97.79, 1)" +
                    ", (null, '2020-01-02 19:02:44', 'Meals', 840.72, 1)" +
                    ", (null, '2020-01-16 23:07:55', 'Transportation', 23.39, 1)" +
                    ", (null, '2020-03-19 16:57:39', 'Education', 755.85, 1)" +
                    ", (null, '2020-02-21 14:09:12', 'Education', 908.47, 1)" +
                    ", (null, '2020-02-25 00:17:02', 'Education', 824.4, 1)" +
                    ", (null, '2020-01-13 01:06:03', 'Entertainment', 499.66, 1)" +
                    ", (null, '2020-01-30 01:47:31', 'Meals', 574.08, 1)" +
                    ", (null, '2020-03-23 14:34:57', 'Entertainment', 320.81, 1)" +
                    ", (null, '2020-02-07 14:08:57', 'Transportation', 159.91, 1)" +
                    ", (null, '2020-02-27 01:13:56', 'Transportation', 627.21, 1)" +
                    ", (null, '2020-03-06 21:55:36', 'Education', 864.43, 1)" +
                    ", (null, '2020-02-17 19:30:23', 'Entertainment', 377.05, 1)" +
                    ", (null, '2020-03-15 06:51:04', 'Education', 187.5, 1)" +
                    ", (null, '2020-01-24 08:04:48', 'Meals', 595.52, 1)" +
                    ", (null, '2020-02-20 09:03:08', 'Entertainment', 657.91, 1)" +
                    ", (null, '2020-03-10 20:39:32', 'Meals', 100.21, 1)" +
                    ", (null, '2020-03-15 15:59:10', 'Entertainment', 987.76, 1)" +
                    ", (null, '2020-01-17 15:30:50', 'Meals', 155.1, 1)" +
                    ", (null, '2020-03-29 07:08:05', 'Education', 248.67, 1)" +
                    ", (null, '2020-02-29 22:20:44', 'Meals', 416.77, 1)" +
                    ", (null, '2020-03-11 04:33:30', 'Meals', 163.4, 1)" +
                    ", (null, '2020-02-02 13:30:18', 'Transportation', 84.0, 1)" +
                    ", (null, '2020-03-30 04:16:28', 'Meals', 320.93, 1)" +
                    ", (null, '2020-01-22 13:51:16', 'Meals', 812.95, 1)" +
                    ", (null, '2020-02-07 01:16:59', 'Transportation', 592.94, 1)" +
                    ", (null, '2020-01-18 20:38:00', 'Entertainment', 678.84, 1)" +
                    ", (null, '2020-03-19 01:14:51', 'Meals', 512.42, 1)" +
                    ", (null, '2020-01-25 18:44:50', 'Entertainment', 105.37, 1)" +
                    ", (null, '2020-03-29 00:50:24', 'Entertainment', 499.84, 1)" +
                    ", (null, '2020-01-01 18:29:50', 'Education', 683.74, 1)" +
                    ", (null, '2020-01-27 17:59:55', 'Education', 129.21, 1)" +
                    ", (null, '2020-03-21 16:14:57', 'Meals', 355.85, 1)" +
                    ", (null, '2020-03-07 04:39:29', 'Transportation', 507.81, 1)" +
                    ", (null, '2020-01-31 20:05:31', 'Entertainment', 163.49, 1)" +
                    ", (null, '2020-03-18 15:20:59', 'Transportation', 76.46, 1)" +
                    ", (null, '2020-01-18 05:30:22', 'Education', 525.55, 1)" +
                    ", (null, '2020-03-18 13:08:38', 'Meals', 369.3, 1)" +
                    ", (null, '2020-03-03 18:08:22', 'Meals', 567.05, 1)" +
                    ", (null, '2020-02-06 17:49:25', 'Transportation', 328.68, 1)" +
                    ", (null, '2020-01-06 20:37:16', 'Meals', 118.08, 1)" +
                    ", (null, '2020-01-13 15:43:40', 'Education', 425.63, 1)" +
                    ", (null, '2020-02-10 11:35:09', 'Entertainment', 230.15, 1)" +
                    ", (null, '2020-02-04 22:47:39', 'Education', 845.77, 1)" +
                    ", (null, '2020-02-21 03:50:39', 'Meals', 453.77, 1)" +
                    ", (null, '2020-01-05 22:18:06', 'Entertainment', 500.21, 1)" +
                    ", (null, '2020-01-13 18:40:41', 'Meals', 414.1, 1)" +
                    ", (null, '2020-02-28 18:11:17', 'Education', 675.02, 1)" +
                    ", (null, '2020-03-26 13:21:47', 'Entertainment', 17.43, 1)" +
                    ", (null, '2020-02-23 04:41:48', 'Transportation', 357.95, 1)" +
                    ", (null, '2020-01-10 16:04:14', 'Entertainment', 386.49, 1)" +
                    ", (null, '2020-01-22 22:26:40', 'Education', 107.16, 1)" +
                    ", (null, '2020-03-03 08:07:59', 'Education', 632.45, 1)" +
                    ", (null, '2020-02-22 11:16:40', 'Education', 465.9, 1)" +
                    ", (null, '2020-01-21 14:14:56', 'Transportation', 18.84, 1)" +
                    ", (null, '2020-02-19 08:49:28', 'Meals', 439.05, 1)" +
                    ", (null, '2020-02-05 07:08:16', 'Transportation', 131.24, 1)";
}
