/**
 * csm
 * cqcdi.web.scm.config
 * <p>
 * Copyright © 2018 重庆市信息通信咨询设计院有限公司.版权所有.
 * 重庆市九龙坡区科园四路257号,400041.
 * <p>
 * 此软件未经重庆市信息通信咨询设计院有限公司许可，严禁发布、传播、使用.
 */
package com.chaojilaji.meedudata.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * thymeleaf 配置.
 *
 * @author yzw
 * @version 1.0   2018/5/4
 */
@Configuration
public class ThymeleafConfig implements WebMvcConfigurer {
    public static final String DATATIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    public static final DateTimeFormatter DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS = DateTimeFormatter.ofPattern(DATATIME_FORMAT);

    /**
     * 添加自定义序列化反序列化对象
     *
     * @param registry 注册器
     */
    @Override
    public void addFormatters(final FormatterRegistry registry) {
        registry.addFormatter(new LocalDateTimeFormatter());
        registry.addFormatter(new TimestampFormatter());
    }

    /**
     * LocalDateTime 格式化器.
     *
     * @author yzw
     * @version 1.0   2018/5/4
     */
    class TimestampFormatter implements Formatter<Timestamp> {

        /**
         * 反序列化
         *
         * @param text   日期字符串
         * @param locale 地区信息
         * @return LocalDateTime 日期对象
         */
        @Override
        public Timestamp parse(String text, Locale locale) throws ParseException {
            // TODO 实现功能
            return null;
        }

        /**
         * 序列化
         *
         * @param timestamp 日期对象
         * @param locale        地区信息
         * @return 日期字符串
         */
        @Override
        public String print(Timestamp timestamp, Locale locale) {
            DateTimeFormatter dateTimeFormatter = ThymeleafConfig.DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS;
//            if (Objects.nonNull(locale) && !locale.equals(DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS.getLocale())) {
//                DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS = DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS.withLocale(locale);
//            }
            return dateTimeFormatter.format(timestamp.toLocalDateTime());
        }
    }

    /**
     * LocalDateTime 格式化器.
     *
     * @author yzw
     * @version 1.0   2018/5/4
     */
    class LocalDateTimeFormatter implements Formatter<LocalDateTime> {

        /**
         * 反序列化
         *
         * @param text   日期字符串
         * @param locale 地区信息
         * @return LocalDateTime 日期对象
         */
        @Override
        public LocalDateTime parse(String text, Locale locale) throws ParseException {
            // TODO 实现功能
            return null;
        }

        /**
         * 序列化
         *
         * @param localDateTime 日期对象
         * @param locale        地区信息
         * @return 日期字符串
         */
        @Override
        public String print(LocalDateTime localDateTime, Locale locale) {
            DateTimeFormatter dateTimeFormatter = ThymeleafConfig.DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS;
//            if (Objects.nonNull(locale) && !locale.equals(DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS.getLocale())) {
//                DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS = DATETIME_FORMATTER_YYYY_MM_DD_HH_MM_SS.withLocale(locale);
//            }
            return dateTimeFormatter.format(localDateTime);
        }
    }
}
