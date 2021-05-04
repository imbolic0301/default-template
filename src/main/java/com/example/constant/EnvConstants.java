package com.example.constant;

import java.util.Collections;
import java.util.Map;

public interface EnvConstants {
	Map<String, String> OK = Collections.singletonMap("is_success", "Y");
	Map<String, String> FAIL = Collections.singletonMap("is_success", "N");
}	
