import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Obfuscator {
    private static final String CHARSET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
    private static final Random RANDOM = new Random();
    private static final Set<String> usedNames = new HashSet<>(); // 중복 방지용 집합
    private static final HashMap<String, String> nameMap = new HashMap<>(); // 원래 변수명 → 난독화된 변수명

    // 12자에서 25자 사이의 난독화된 문자열 생성 (첫 문자는 영문자 또는 _)
    public static String generateUniqueRandomString() {
        int length = 12 + RANDOM.nextInt(14); // 12에서 25 사이의 랜덤 길이
        String randomStr;
        do {
            randomStr = generateRandomString(length);
        } while (usedNames.contains(randomStr)); // 중복되면 다시 생성

        usedNames.add(randomStr); // 생성된 이름을 사용 목록에 추가
        return randomStr;
    }

    private static String generateRandomString(int length) {
        StringBuilder sb = new StringBuilder(length);

        // 첫 번째 문자는 반드시 영문자 또는 _
        int firstCharIndex = RANDOM.nextInt(53); // a-z, A-Z, _ 중에서
        sb.append(CHARSET.charAt(firstCharIndex));

        // 나머지 문자는 영문자, 숫자, _ 모두 사용 가능
        for (int i = 1; i < length; i++) {
            int index = RANDOM.nextInt(CHARSET.length());
            sb.append(CHARSET.charAt(index));
        }

        return sb.toString();
    }

    // 변수명이 이미 난독화된 이름으로 존재하면 반환, 아니면 새로운 난독화 이름 생성
    public static String obfuscateVariableName(String originalName) {
        if (nameMap.containsKey(originalName)) {
            return nameMap.get(originalName); // 이미 난독화된 이름이 있으면 반환
        }

        String obfuscatedName = generateUniqueRandomString(); // 12~25자 길이의 난독화된 이름 생성
        nameMap.put(originalName, obfuscatedName);
        return obfuscatedName;
    }
}
