package Utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.tools.javac.Main;

import java.io.InputStream;

public class DataProvider {

    public static String jsonreader(String key) {
        try (InputStream is = DataProvider.class.getClassLoader()
                .getResourceAsStream("Test_Data.json")) {
            JsonNode data = new ObjectMapper().readTree(is);
            return data.get(key).asText();
        } catch (Exception e) {
            throw new RuntimeException("Failed to read testdata.json", e);
        }

    }
}