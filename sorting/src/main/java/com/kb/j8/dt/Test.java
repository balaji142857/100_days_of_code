package com.kb.j8.dt;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Test {
	
	
	public static void main(String[] args) throws InterruptedException {
		LocalDateTime ldt1 = utilToLdt(new Date());
		TimeUnit.SECONDS.sleep(3);
		LocalDateTime ldt2 = utilToLdt(new Date());
		System.out.println(Duration.between(ldt1, ldt2));
		
		Instant instant = Instant.now();
		System.out.println(instant);
		System.out.println("utc: \t\t"+LocalDateTime.ofInstant(instant, ZoneId.of("UTC")));
		System.out.println("paris:\t\t"+ LocalDateTime.ofInstant(instant, ZoneId.of("Europe/Paris")));
		System.out.println("calcutta: \t"+LocalDateTime.ofInstant(instant, ZoneId.of("Asia/Calcutta")));
	}
	
	public static LocalDateTime utilToLdt(Date date) {
		return LocalDateTime.ofInstant(date.toInstant(), ZoneId.of("UTC"));
	}
	
	public static Date ldtToDate(LocalDateTime ldt) {
		return Date.from(ldt.toInstant(ZoneOffset.UTC));
	}
	
	public static Date ZdtToDate(ZonedDateTime zdt) {
		return Date.from(zdt.toInstant());
	}
	
	public static ZonedDateTime dateToZdt(Date date, String zoneId) {
		return ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of(zoneId));
	}
	
	public static LocalDateTime zdtToLdt(ZonedDateTime zdt) {
		return zdt.toLocalDateTime();
	}
	
	public static ZonedDateTime ldtToZdt(LocalDateTime ldt, String zoneId) {
		return ldt.atZone(ZoneId.of(zoneId));
	}

}
