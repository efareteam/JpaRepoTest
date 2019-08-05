package com.ibaseit.JpaRepoTest.util;

import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.JSON_PATH;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.UNCHECKED;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_CODE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_CODE_417;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_CODE_500;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.ERROR_MSG;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.FAILED;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.FAILURE_RESP;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SCHEMA_PATH;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SUCCESS;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SUCCESS_CODE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SUCCESS_RESP;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.CHILDREN;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.EMAIL_PATTERN;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.INVALID;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.JSON_SCHEMA_IDENTIFIER_ELEMENT;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.JSON_V4_SCHEMA_IDENTIFIER;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.MISSING;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.PATTERN_NOT_MATCH;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.POINTER;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.RESPONSE_OBJECT;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SCHEMA;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.SLASH;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.STATUS_CODE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.STATUS_FLAG;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.STATUS_MESSAGE;
import static com.ibaseit.JpaRepoTest.constants.ValidationUtilConstants.VALUE;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ListProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import com.google.gson.Gson;


@Component
@PropertySource(SCHEMA_PATH)
public class InputValidationUtil {

	public static boolean validate(final String emailId) {
		//LOGGER.info("validate started");
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(emailId);
		//LOGGER.info("validate ended");
		return matcher.matches();
	}

	public static Map<String, Object> errorResponse(String errCode, Object errResMsg) {
		//LOGGER.info("Error Response Started");
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(STATUS_CODE, errCode);
		responseMap.put(STATUS_FLAG, 1);
		responseMap.put(STATUS_MESSAGE, errResMsg);
		//LOGGER.info("Error Response Ended");
		return responseMap;
	}

	public static Map<String, Object> successResponse(Object msg) {
		//LOGGER.info("success Response Started");
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(STATUS_CODE, SUCCESS_CODE);
		responseMap.put(STATUS_FLAG, 0);
		responseMap.put(STATUS_MESSAGE, SUCCESS);
		responseMap.put(RESPONSE_OBJECT, msg);
		//LOGGER.info("success Response Ended");
		return responseMap;
	}

	@SuppressWarnings(UNCHECKED)
	public static Map<String, Object> objectSuccessResponse(Object msg) {
		//LOGGER.info("object Success Response Started");
		Gson jsonObject = new Gson();
		String resObj = jsonObject.toJson(msg);
		Map<String, Object> response = new HashMap<>();
		try {
			response = new ObjectMapper().readValue(resObj, HashMap.class);
		} catch (Exception excep) {
			errorResponse(ERROR_CODE_500, excep.getLocalizedMessage());
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put(STATUS_CODE, SUCCESS_CODE);
		responseMap.put(STATUS_FLAG, 0);
		responseMap.put(STATUS_MESSAGE, SUCCESS);
		responseMap.put(RESPONSE_OBJECT, response);
		//LOGGER.info("object Success Response Ended");
		return responseMap;
	}

	@SuppressWarnings(UNCHECKED)
	public static Map<String, Object> getMapResponse(Object msg) {
		//LOGGER.info("Get Map Response Started");
		Gson jsonObject = new Gson();
		String resObj = jsonObject.toJson(msg);
		Map<String, Object> response = new HashMap<>();
		try {
			response = new ObjectMapper().readValue(resObj, HashMap.class);
		} catch (Exception excep) {
			errorResponse(ERROR_CODE_500, excep.getLocalizedMessage());
		}
		//LOGGER.info("Get Map Response Ended");
		return response;
	}

	@SuppressWarnings(UNCHECKED)
	public static <T> List<Map<String, Object>> getListMapResponse(List<T> reqmMsg) {
		//LOGGER.info("Get List Map Response Started");
		List<Map<String, Object>> listMap = new ArrayList<>();
		for (Object msg : reqmMsg) {

			Gson jsonObject = new Gson();
			String resObj = jsonObject.toJson(msg);
			Map<String, Object> response = new HashMap<>();
			try {
				response = new ObjectMapper().readValue(resObj, HashMap.class);
			} catch (Exception excep) {
				errorResponse(ERROR_CODE_500, excep.getLocalizedMessage());
			}
			listMap.add(response);
		}
		//LOGGER.info("Get List Map Response Ended");
		return listMap;
	}

	public static <T> Set<ConstraintViolation<T>> validateObject(T object) {
		//LOGGER.info("validate Object Started");
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		//LOGGER.info("validate Object Ended");
		return validator.validate(object);
	}

	public static <T> Boolean emptyCheck(T objJson) {
		//LOGGER.info("empty Check Started");
		JSONObject jsonObject = new JSONObject(new Gson().toJson(objJson));
		if (jsonObject.length() == 0) {
			return true;
		}
		//LOGGER.info("empty Check Ended");
		return false;
	}

	/*public static CortexObjectMapper getCortexObjectMapper() {
		LOGGER.info("get CortexObjectMapper Started");
		return new CortexObjectMapper();
	}*/

	public static Authentication getAuthentication() {
		//LOGGER.info("get Authentication Started");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return auth;
	}

	public static Map<String, Object> ValidateJson(JsonNode jsonFileNode, JsonNode jsonNode) {
		//LOGGER.info("Validate Json Started");
		Map<String, Object> respMsg = new HashMap<>();
		try {
			// JsonSchema schemaNode = getValidationSchemaNode(schemaFile);
			JsonSchema schemaNode = getNode(jsonFileNode);
			ListProcessingReport report = (ListProcessingReport) schemaNode.validate(jsonNode);
			if (report.isSuccess()) {
				respMsg.put(SUCCESS, SUCCESS_RESP);
			} else {
				JsonNode jsNode = report.asJson();
				JSONObject jsonObject = new JSONObject(new Gson().toJson(jsNode));
				JSONArray jsArry = jsonObject.optJSONArray(CHILDREN);
				List<Object> errList = new ArrayList<>();
				if (jsArry.length() > 0) {
					JSONObject jsonObjIndex = jsArry.getJSONObject(0);
					JSONObject jsonSubObj = jsonObjIndex.getJSONObject(CHILDREN);
					if (jsonSubObj.has(MISSING)) {
						JSONObject jsonChild = jsonSubObj.getJSONObject(MISSING);
						JSONArray jsonArrObj = jsonChild.getJSONArray(CHILDREN);
						for (int i = 0; i < jsonArrObj.length(); i++) {
							JSONObject jsonLastObj = (JSONObject) jsonArrObj.get(i);
							String string = jsonLastObj.getString(VALUE);
							String str = string + INVALID;
							errList.add(str);
						}
					} else {
						if (jsonSubObj.has(SCHEMA)) {
							JSONObject jsonPatrnObj = jsonSubObj.getJSONObject(SCHEMA).getJSONObject(CHILDREN)
									.getJSONObject(POINTER);
							String[] strArr = jsonPatrnObj.getString(VALUE).split(SLASH);
							String strOutput = strArr[strArr.length - 1] + PATTERN_NOT_MATCH;
							errList.add(strOutput);
						} else {
							report.forEach(y -> {
								errList.add(y.getMessage());
							});
						}
					}
					respMsg.put(FAILED, FAILURE_RESP);
					respMsg.put(ERROR_CODE, ERROR_CODE_417);
					respMsg.put(ERROR_MSG, errList);
					return respMsg;
				}
			}
		} catch (ProcessingException excep) {
			respMsg.put(FAILED, FAILURE_RESP);
			respMsg.put(ERROR_CODE, ERROR_CODE_500);
			respMsg.put(ERROR_MSG, excep.getLocalizedMessage());
			return respMsg;
		}
		//LOGGER.info("Validate Json Ended");
		return respMsg;
	}

	public static JsonSchema getValidationSchemaNode(File schemaFile) throws IOException, ProcessingException {
		//LOGGER.info("Get ValidationSchemaNode Started");
		final JsonNode schemaNode = getJsonNode(schemaFile);
		//LOGGER.info("Get ValidationSchemaNode Ended");
		return getNode(schemaNode);
	}

	private static JsonSchema getNode(JsonNode jsonNode) throws ProcessingException {
		//LOGGER.info("Get Node Started");
		final JsonNode schemaIdentifier = jsonNode.get(JSON_SCHEMA_IDENTIFIER_ELEMENT);
		if (null == schemaIdentifier) {
			((ObjectNode) jsonNode).put(JSON_SCHEMA_IDENTIFIER_ELEMENT, JSON_V4_SCHEMA_IDENTIFIER);
		}

		final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
		//LOGGER.info("Get Node Ended");
		return factory.getJsonSchema(jsonNode);
	}

	public static JsonNode getJsonNode(File jsonFile) throws IOException {
		//LOGGER.info("Get JsonNode Started");
		return JsonLoader.fromFile(jsonFile);
	}

	public static Map<String, Object> validateJsonInputFields(Object inputJson, String name) throws IOException {
		//LOGGER.info("validate Json InputFields Started");
		InputStream inputStream = Thread.currentThread().getContextClassLoader().getResource(JSON_PATH+"/" + "validation_schema.json").openStream();
		Reader inputStreamReader = new InputStreamReader(inputStream);
		JsonNode jsonFileNode = JsonLoader.fromReader(inputStreamReader);

		Map<String, Object> mapObj = new HashMap<>();
		mapObj.put(name, inputJson);
		JsonNode node = new ObjectMapper().convertValue(mapObj, JsonNode.class);
		//LOGGER.info("validate Json InputFields Ended");
		return ValidateJson(jsonFileNode, node);
	}
	
	public static Object convertObjToJson(Object javaObj) {
		String json = "";
		ObjectMapper mapper = null;
		try {
			mapper = new ObjectMapper();
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
		ObjectMapper mapper = null;
		try {
			mapper = new ObjectMapper();
			t = mapper.readValue(jsonObj, cls);
		} catch (JsonParseException | JsonMappingException e) {
			System.out.println("Exception to convert json into java object " + e.getMessage());
		} catch (IOException e) {
			System.out.println("Exception to convert json into java object " + e.getMessage());
		}
		return t;
	}

}
