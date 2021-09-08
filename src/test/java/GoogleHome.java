import org.testng.annotations.Test;

public class GoogleHome extends TestBase {
    @Test
    public void validateGoogleHome()
    {
        webDriver.get("https://www.google.com");
        openEyes("validateGoogleHome");
        checkLayout("Google Home");
        checkStrict("Google Home Strict Layout");
        checkStrictRegionAndLayoutRegion("Google Home Layout & Strict modes combined", ".gb_Ca.gbii");
        checkStrictRegionAndLayoutRegion("Google Home Layout & Ignore modes combined", ".gb_Ca.gbii");
        closeEyes();
    }
}
