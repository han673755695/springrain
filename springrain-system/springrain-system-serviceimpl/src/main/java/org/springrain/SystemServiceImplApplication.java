package org.springrain;

import org.springframework.boot.SpringApplication;

/**
 * 作为单体运行时,需要注释 @SpringBootApplication 注解,会冲突,一个项目只能有一个@SpringBootApplication
 * 还需要删除 resources 下的配置文件
 * 
 * @author caomei
 *
 */
//@SpringBootApplication
public class SystemServiceImplApplication {
	public static void main(String[] args) {
		SpringApplication.run(SystemServiceImplApplication.class, args);
	}
}
