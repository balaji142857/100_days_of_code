package com.conditions;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MongoConditional implements Condition {

	public static final String MONGO_CONDITION = "MONGO";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return MONGO_CONDITION.equalsIgnoreCase(System.getProperty("dbType"));
	}

}