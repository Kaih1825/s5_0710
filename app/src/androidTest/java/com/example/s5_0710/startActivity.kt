package com.example.s5_0710

import android.widget.Toast
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers.hasErrorText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.uiautomator.*
import org.hamcrest.CoreMatchers.not

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.manipulation.Ordering.Context

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class startActivity {
    val device=UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())


    @Before
    fun setUp(){
        launch(MainActivity::class.java)
    }

    @Test
    fun emailLogin(){
        try{
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/menuIcon")).click()
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/logout")).click()
        }catch (ex:Exception){}
        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/email")).run{
            click()
            text="abc"
            onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))
            text="abc123@mail.com"
            onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/pwd")).run {
            click()
            text="Abc123"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))
            text="Asdf456!"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/login")).run {
            click()
        }

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/menuIcon")).click()
        device.findObject(UiSelector().text("Name")).run {
            assertEquals("",waitForExists(1000),true)
        }
    }

    @Test
    fun noNameLogin(){
        try{
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/menuIcon")).click()
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/logout")).click()
        }catch (ex:Exception){}
        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/no_name")).run {
            click()
        }
        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/menuIcon")).click()
        device.findObject(UiSelector().text("訪客")).run {
            assertEquals("",waitForExists(1000),true)
        }
    }

    @Test
    fun reg(){
        try{
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/menuIcon")).click()
            device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/logout")).click()
        }catch (ex:Exception){}
        device.findObject(UiSelector().text("註冊")).click()
        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/email")).run{
            click()
            text="abc"
            onView(withId(R.id.email)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))
            text="abc123@mail.com"
            onView(withId(R.id.email)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/pwd")).run {
            click()
            text="Abc123"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(hasErrorText("格式錯誤")))
            text="Asdf456!"
            onView(withId(R.id.pwd)).check(ViewAssertions.matches(not(hasErrorText("格式錯誤"))))
        }

        device.pressBack()

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/country")).run {
            click()
            device.findObject(UiSelector().text("English(US)")).click()
        }
        device.findObject(UiSelector().text("Register")).run {
            assertEquals("",waitForExists(1000),true)
        }

        device.findObject(UiSelector().resourceId("${device.currentPackageName}:id/country")).run {
            click()
            device.findObject(UiSelector().text("繁體中文（臺灣）")).click()
        }
        device.findObject(UiSelector().text("註冊")).run {
            assertEquals("",waitForExists(1000),true)
        }
    }
}