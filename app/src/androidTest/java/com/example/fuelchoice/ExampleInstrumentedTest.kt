package com.example.fuelchoice

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get:Rule()
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun verificaCombustiveisIgualmenteEficiente() {
        //Combustivel 1
        onView(withId(R.id.etConsumo1))
            .perform(typeText("10"))
        onView(withId(R.id.etValor1))
            .perform(typeText("10"))

        //Combustivel 2
        onView(withId(R.id.etConsumo2))
            .perform(typeText("7"))
        onView(withId(R.id.etValor2))
            .perform(typeText("7"))

        onView(withId(R.id.btCalcular))
            .perform(click())

        onView(withId(R.id.tvResultado))
            .check(matches(withText(R.string.os_combustiveis_sao_igualmente_eficientes)))
    }

    @Test
    fun verificaCombustivel1MaisEficiente() {
        //Combustivel 1
        onView(withId(R.id.etConsumo1))
            .perform(typeText("10"))
        onView(withId(R.id.etValor1))
            .perform(typeText("1"))

        //Combustivel 2
        onView(withId(R.id.etConsumo2))
            .perform(typeText("7"))
        onView(withId(R.id.etValor2))
            .perform(typeText("10"))

        onView(withId(R.id.btCalcular))
            .perform(click())

        onView(withId(R.id.tvResultado))
            .check(matches(withText(R.string.combustivel_1_mais_economico)))
    }

    @Test
    fun verificaCombustivel2MaisEficiente() {
        //Combustivel 1
        onView(withId(R.id.etConsumo1))
            .perform(typeText("10"))
        onView(withId(R.id.etValor1))
            .perform(typeText("13"))

        //Combustivel 2
        onView(withId(R.id.etConsumo2))
            .perform(typeText("7"))
        onView(withId(R.id.etValor2))
            .perform(typeText("1"))

        onView(withId(R.id.btCalcular))
            .perform(click())

        onView(withId(R.id.tvResultado))
            .check(matches(withText(R.string.combustivel_2_mais_economico)))
    }
}