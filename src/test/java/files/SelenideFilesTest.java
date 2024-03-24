package files;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

public class SelenideFilesTest {

    @Test
    void selenideDownloadTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File downlodedFile = $("[data-testid='raw-button']").download();
        try (InputStream is = new FileInputStream(downlodedFile)) {
            assertThat(new String(is.readAllBytes(), UTF_8)).contains("This repository is the home of _JUnit 5_.");
        }
//        String readString = Files.readString(downlodedFile.toPath(), UTF_8);
//        способ скачивания файла, добавленный в 7 версии
    }

    @Test
    void uploadSelenideTest() {
        open("https://the-internet.herokuapp.com/upload");
        $("input[type='file']").uploadFromClasspath("files/1.txt");
//        .uploadFile("gолный путь к файлу") - bad practice!
        $("#file-submit").click();
        $("div.example").shouldHave(Condition.text("File uploaded!"));
        $("div#uploaded-files").shouldHave(Condition.text("1.txt"));

    }
}
