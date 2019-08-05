package com.ibaseit.JpaRepoTest.converter;

import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter {

	public static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	public static Object convertObjToJson(Object javaObj) {
		String json = "";
		try {
			String[] jsonArr = mapper.writeValueAsString(javaObj).split(",");
			for (Object obj : Arrays.stream(jsonArr).filter(x -> !x.contains("null")).toArray())
				json = json + obj + ",";

		} catch (JsonProcessingException e) {
			System.out.println("Exception to convert java object into json " + e.getMessage());
		}
		return convertJsonToObj(
				json.substring(0, json.lastIndexOf(",")) + "}" + json.substring(json.lastIndexOf(",") + 1),
				Object.class);
	}

	public static <T> T convertJsonToObj(String jsonObj, Class<T> cls) {
		T t = null;
		try {
			t = mapper.readValue(jsonObj, cls);
		} catch (JsonParseException | JsonMappingException e) {
			System.out.println("Exception to convert json into java object " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception to convert json into java object " + e.getMessage());
		}
		return t;
	}

	/*
	 * public static void main(String[] args) { WebElementDTO ele = new
	 * WebElementDTO(); ele.setElementId("id"); ele.setHtmlElementId(12); String
	 * json = convertObjToJson(ele); System.out.println(json); System.out.println(
	 * "============================================================");
	 * WebElementDTO obj = convertJsonToObj(json, WebElementDTO.class);
	 * System.out.println(obj.getElementId() + "  " + obj.getHtmlElementId()); }
	 */
}
