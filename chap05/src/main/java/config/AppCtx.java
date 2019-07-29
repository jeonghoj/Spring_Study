package config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import org.springframework.stereotype.Component;
import spring.ChangePasswordService;
import spring.MemberDao;
import spring.MemberInfoPrinter;
import spring.MemberListPrinter;
import spring.MemberPrinter;
import spring.MemberRegisterService;
import spring.MemberSummaryPrinter;
import spring.VersionPrinter;

@Configuration
@ComponentScan(basePackages = {"spring"})
public class AppCtx {

	//컴포넌트 스캔 기능 덕분에 필요없어진 코드

//
//	@Bean
//	public MemberDao memberDao() {
//		return new MemberDao();
//	}
//
//	@Bean
//	public MemberRegisterService memberRegSvc() {
//		return new MemberRegisterService();
//	}
//
//	@Bean
//	public ChangePasswordService changePwdSvc() {
//		return new ChangePasswordService();
//	}
//

	//
//	@Bean
//	public MemberListPrinter listPrinter() {
//		return new MemberListPrinter();
//	}
//
//	@Bean
//	public MemberInfoPrinter infoPrinter() {
//		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
//		return infoPrinter;
//	}
//

	@Bean
	@Qualifier("printer")
	public MemberPrinter memberPrinter1() {
		return new MemberPrinter();
	}
	
	@Bean
	@Qualifier("summaryPrinter")
	public MemberSummaryPrinter memberPrinter2() {
		return new MemberSummaryPrinter();
	}

	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
}
