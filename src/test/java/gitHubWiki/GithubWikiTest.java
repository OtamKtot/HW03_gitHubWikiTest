package gitHubWiki;

import org.junit.jupiter.api.BeforeAll;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class GithubWikiTest {
    @BeforeAll
    static void beforeAll() { Configuration.pageLoadStrategy = "eager"; }

    @Test
    void GithubWikiCheckTest() {
        //Open Selenide Wiki
        open("https://github.com/selenide/selenide/wiki");
        //Find text 'SoftAssertions' and click
        $("#wiki-pages-filter").setValue("soft").pressEnter();
        $("#wiki-pages-box").$(byText("SoftAssertions")).click();
        $("#wiki-pages-box").shouldHave(Condition.text("SoftAssertions"));
        $(byText("SoftAssertions")).click();
        //check up
        $(".markdown-body").shouldHave(text("@ExtendWith({SoftAssertsExtension.class})\n" +
                "class Tests {\n" +
                "  @Test\n" +
                "  void test() {\n" +
                "    Configuration.assertionMode = SOFT;\n" +
                "    open(\"page.html\");\n" +
                "\n" +
                "    $(\"#first\").should(visible).click();\n" +
                "    $(\"#second\").should(visible).click();\n" +
                "  }\n" +
                "}"));
    }

}
