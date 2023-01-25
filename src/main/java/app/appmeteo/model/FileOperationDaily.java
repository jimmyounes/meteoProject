package app.appmeteo.model;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileOperationDaily {
    public static void replaceJsonFile(StringBuffer responseContent) throws IOException {
        Path path = Paths.get("src/main/resources/Daily.json");
        Charset charset = StandardCharsets.UTF_8;

        String content = new String(Files.readAllBytes(path), charset);
        content = responseContent.toString();
        Files.write(path, content.getBytes(charset));
    }
}
