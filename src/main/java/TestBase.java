import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.DesktopBrowserInfo;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

public class TestBase
{
    protected EyesRunner runner;
    protected Configuration suiteConfig;
    protected Eyes eyes;
    protected static BatchInfo batch;
    protected String apiKey = "set your applitools eyes apiKey here";
    RectangleSize viewPortSize = new RectangleSize(1920, 1080);
    private int concurrentSessions = 10;
    protected int viewPortWidth = 1920;
    protected int viewPortHeight = 1080;
    protected WebDriver webDriver;

    @Parameters("runnerType")
    @BeforeSuite()
    public void setup(String runnerType)
    {
        System.out.println("runnerType: " + runnerType);
        WebDriverManager.chromedriver().setup();
        if(runnerType.contains("classic"))
        {
            runner = new ClassicRunner();
            eyes = new Eyes(runner);
            batch = new BatchInfo("Classic Runner");
            eyes.setBatch(batch);
            System.out.println("Classic runner initiated");
        }
        else
        {
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(concurrentSessions));
            System.out.println("Visual grid initiated");
            suiteConfig = (Configuration) new Configuration()
                    .addBrowser(new DesktopBrowserInfo(viewPortWidth, viewPortHeight, BrowserType.CHROME))
                    .addBrowser(new DesktopBrowserInfo(viewPortWidth, viewPortHeight, BrowserType.EDGE_CHROMIUM))
                    .addBrowser(new DesktopBrowserInfo(viewPortWidth, viewPortHeight, BrowserType.SAFARI));
            eyes = new Eyes(runner);
            eyes.setConfiguration(suiteConfig);
            batch = new BatchInfo("Ultra-fast Grid");
            eyes.setBatch(batch);
        }
        webDriver = new ChromeDriver(new ChromeOptions().setHeadless(true));
        eyes.setLogHandler(new FileLogger("eyes-runner.log",false, true ));
        eyes.setApiKey(apiKey);
        eyes.setStitchMode(StitchMode.CSS);
        eyes.setHideScrollbars(true);
        eyes.setHideCaret(true);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDownEyes()
    {
        eyes.abortIfNotClosed();
        TestResultsSummary allTestResults = runner.getAllTestResults(true);
    }

    public void openEyes(String testName)
    {
        eyes.open(webDriver, "Google", testName);
    }

    public void checkLayout(String tag)
    {
        eyes.check(tag, Target.window().layout().useDom(true).enablePatterns(true));
    }

    public void closeEyes()
    {
        eyes.closeAsync();
    }

    public void checkStrictRegionAndLayoutRegion(String tag, String strictRegionLocator)
    {
        eyes.check(tag, Target.window().fully().layout(By.cssSelector(strictRegionLocator)).strict());
    }

    public void checkStrict(String tag)
    {
        eyes.check(tag, Target.window().strict());
    }
}
