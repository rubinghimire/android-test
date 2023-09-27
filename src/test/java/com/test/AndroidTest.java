package com.test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AutomationName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;


public class AndroidTest {

    @Test
    public void androidLaunchTest() throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional -- set by default
        options.setAutomationName("UIAutomator2"); //optional -- set by default
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        //Thread.sleep(5000);
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));

        tap(driver, openMenu);

//
//        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
//        //Thread.sleep(10000);
//        //new WebDriverWait(driver, Duration.ofSeconds(10)).until(e->e.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")));
//        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item log in\"]")).click();
//        //Thread.sleep(5000);
//        driver.findElement(AppiumBy.accessibilityId("Username input field")).sendKeys("websfkj");


    }

    @Test
    public void doubleTapTest() throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional -- set by default
        options.setAutomationName("UIAutomator2"); //optional -- set by default
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir")+"/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        //Thread.sleep(5000);
        WebElement openMenu = driver.findElement(AppiumBy.accessibilityId("open menu"));

        doubleTap(driver, openMenu);

    }

    @Test
    void zoom() throws MalformedURLException, InterruptedException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir") + "/apps/Android-MyDemoAppRN.1.3.0.build-244.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        driver.findElement(AppiumBy.accessibilityId("open menu")).click();
        driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"menu item drawing\"]"))
                .click();
        WebElement element = driver.findElement(By.xpath("//android.view.ViewGroup[@content-desc=\"drawing screen\"]"));
        Point centerOfElement = getCenterOfElement(element.getLocation(), element.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        PointerInput finger2 = new PointerInput(PointerInput.Kind.TOUCH, "finger2");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() + 100,
                        centerOfElement.getY() - 100))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        Sequence sequence2 = new Sequence(finger2, 1)
                .addAction(finger2.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger2.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger2, Duration.ofMillis(200)))
                .addAction(finger2.createPointerMove(Duration.ofMillis(200),
                        PointerInput.Origin.viewport(), centerOfElement.getX() - 100,
                        centerOfElement.getY() + 100))
                .addAction(finger2.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(sequence, sequence2));

        //Assertion
    }

    @Test
    void swipeOrScroll() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();

        Dimension size = driver.manage().window().getSize();
        int startX = size.getWidth() / 2;
        int startY = size.getHeight() / 2;
        int endY = (int) (size.getHeight() / 4);
        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerMove(Duration.ofMillis(100), PointerInput.Origin.viewport(), startX, endY))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
        //Assertion
    }


    @Test
    void dragAndDrop() throws MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional
        options.setAutomationName(AutomationName.ANDROID_UIAUTOMATOR2);//optional
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir") + "/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();
        driver.findElement(AppiumBy.xpath(".//*[@text='Drag and Drop']")).click();

        WebElement source = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_1"));
        WebElement target = driver.findElement(AppiumBy.id("io.appium.android.apis:id/drag_dot_2"));

        Point sourceElementCenter = getCenterOfElement(source.getLocation(), source.getSize());
        Point targetElementCenter = getCenterOfElement(target.getLocation(), target.getSize());

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");
        Sequence sequence = new Sequence(finger1, 1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), sourceElementCenter))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction((new Pause(finger1, Duration.ofMillis(500))))
                .addAction(finger1.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), targetElementCenter))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));

    }

    @Test
    public void longPressTest() throws InterruptedException, MalformedURLException {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android"); //optional -- set by default
        options.setAutomationName("UIAutomator2"); //optional -- set by default
        options.setDeviceName("rubin-test-device");
        options.setApp(System.getProperty("user.dir")+"/apps/ApiDemos-debug.apk");

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);

        driver.findElement(AppiumBy.xpath(".//*[@text='Views']")).click();
        driver.findElement(AppiumBy.xpath(".//*[@text='Expandable Lists']")).click();
        driver.findElement(AppiumBy.xpath(".//*[@text='1. Custom Adapter']")).click();
        WebElement element = driver.findElement(AppiumBy.xpath("//*[@text='People Names']"));

        longPress(driver, element);
        //new Actions(driver).clickAndHold(element).perform(); //another way of longpressing
    }

    private void longPress(AndroidDriver driver, WebElement element){
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence;
        sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofSeconds(2)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private void doubleTap(AndroidDriver driver, WebElement element){
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence;
        sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100))) //100 because double tap needs two taps in sequence "quickly"
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100))) //100 because double tap needs two taps in sequence "quickly"
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(100))) //100 because double tap needs two taps in sequence "quickly"
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private void tap(AndroidDriver driver, WebElement element){
        Point location = element.getLocation();
        Dimension size = element.getSize();
        Point centerOfElement = getCenterOfElement(location, size);

        PointerInput finger1 = new PointerInput(PointerInput.Kind.TOUCH, "finger1");

        Sequence sequence;
        sequence = new Sequence(finger1,1)
                .addAction(finger1.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerOfElement))
                .addAction(finger1.createPointerDown(PointerInput.MouseButton.LEFT.asArg()))
                .addAction(new Pause(finger1, Duration.ofMillis(200)))
                .addAction(finger1.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(sequence));
    }

    private Point getCenterOfElement(Point location, Dimension size) {
        return new Point(location.getX() + size.getWidth()/2, location.getY()+size.getHeight()/2);
    }
}
