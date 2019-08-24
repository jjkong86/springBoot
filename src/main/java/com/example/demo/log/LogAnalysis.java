package com.example.demo.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class LogAnalysis {

	static final String INPUT_FILE_NAME = "input.log";
	static final String OUTPUT_FILE_NAME = "output.log";
	static final String SUCCESS_CODE = "200";
	static final String SUCCESS_URL = "http://apis.daum.net/search/";
	static final int SERVICEID_SIZE = 3;
	static int browsersCount = 0;

	static Map<String, Integer> apiKeys = new HashMap<>();
	static Map<String, Integer> ServiceIds = new HashMap<>();
	static Map<String, Integer> browsers = new HashMap<>();

	public static String[] splitLog(String readLine) {
		readLine = readLine.startsWith("[") && readLine.endsWith("]") ? readLine.substring(1, readLine.length() - 1)
				: readLine;
		if (!readLine.startsWith(SUCCESS_CODE)) return new String[] {};
		String[] split = readLine.split("]\\[");
		return split;
	}

	public static void logAnalysis(String[] array) {
		browsersCount++;
		ApiAnalysis(array[1]);
		mapCountIncrease(browsers, array[2]);
	}

	public static void ApiAnalysis(String url) {
		url = url.replace(SUCCESS_URL, "");
		String[] split = url.split("\\?");
		String serviceId = split[0];
		mapCountIncrease(ServiceIds, serviceId);

		String apiKey = split[1].replace("apikey=", "").substring(0, 4); // key는 4자리 문자열
		mapCountIncrease(apiKeys, apiKey);
	}

	public static void mapCountIncrease(Map<String, Integer> map, String key) {
		map.put(key, map.getOrDefault(key, 0) + 1);
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, int limit) {
		return map.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(limit)
				.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));
	}

	public static String outputLogMaking(String callManyApiKey, Map<String, Integer> ServiceIds,
			Map<String, Integer> browsers) {
		StringBuilder sb = new StringBuilder();
		sb.append("최다호출 API KEY\n").append(callManyApiKey + "\n\n");
		sb.append("상위 " + SERVICEID_SIZE + "개의 API ServiceID와 각각의 요청 수\n");
		sb.append(mapToString(ServiceIds) + "\n\n");
		sb.append("웹브라우저별 사용 비율\n");
		sb.append(mapToStringAndBrowsers(browsers) + "\n");

		return sb.toString();
	}

	public static String mapToString(Map<String, Integer> map) {
		StringJoiner sj = new StringJoiner("\n");
		for (Entry<String, Integer> entry : map.entrySet()) {
			sj.add(entry.getKey() + " : " + entry.getValue());
		}
		return sj.toString();
	}

	public static String mapToStringAndBrowsers(Map<String, Integer> map) {
		StringJoiner sj = new StringJoiner("\n");
		for (Entry<String, Integer> entry : map.entrySet()) {
			sj.add(entry.getKey() + " : " + (entry.getValue() * 100) / browsersCount + "%");
		}
		return sj.toString();
	}

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME))) {
			while (br.ready()) {
				String[] split = splitLog(br.readLine());
				if (split.length < 1) continue;
				logAnalysis(split);
			}
		}

		String callManyApiKey = apiKeys.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).findFirst().get().getKey();
		ServiceIds = sortByValue(ServiceIds, SERVICEID_SIZE);
		browsers = sortByValue(browsers, browsers.size());

		String output = outputLogMaking(callManyApiKey, ServiceIds, browsers);
		System.out.println(output);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(OUTPUT_FILE_NAME))) {
			writer.write(output);
		}
	}
}
