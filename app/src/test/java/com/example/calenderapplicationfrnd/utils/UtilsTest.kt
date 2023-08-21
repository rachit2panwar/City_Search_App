package com.example.calenderapplicationfrnd.utils

import org.junit.Assert.*
import org.junit.Before

import org.junit.Test
import java.time.LocalDate
import java.time.Month

class UtilsTest {

    private lateinit var calendarUtils: Utils

    @Before
    fun setUp() {
        calendarUtils = Utils
    }

    @Test
    fun testDaysInMonthArray_JanStartsOnSunday() {
        val date = LocalDate.of(2023, Month.JANUARY, 6) // A Sunday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_FebStartsOnWed() {
        val date = LocalDate.of(2023, Month.FEBRUARY, 6) // A Sunday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_StartsOnSunday() {
        val date = LocalDate.of(2023, Month.AUGUST, 6) // A Sunday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_StartsOnFriday() {
        val date = LocalDate.of(2023, Month.SEPTEMBER, 6) // A Sunday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_StartsOnMonday() {
        val date = LocalDate.of(2023, Month.AUGUST, 7) // A Monday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_With7OfMonth() {
        val date = LocalDate.of(2023, Month.AUGUST, 7) // A Monday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_EndsOnSaturday() {
        val date = LocalDate.of(2023, Month.AUGUST, 26) // A Saturday
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_LeapYear() {
        val date = LocalDate.of(2024, Month.FEBRUARY, 1) // A leap year February
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_LowerBoundary() {
        val date = LocalDate.of(2024, Month.JANUARY, 1) // First day of the year
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(35, result.size)
    }

    @Test
    fun testDaysInMonthArray_UpperBoundary() {
        val date = LocalDate.of(2023, Month.DECEMBER, 31) // Last day of the year
        val result = calendarUtils.daysInMonthArray(date)
        assertEquals(42, result.size)
    }

    @Test
    fun testMonthYearFromDate_ValidDate() {
        val date = LocalDate.of(2023, Month.AUGUST, 1)
        val result = calendarUtils.monthYearFromDate(date)
        assertEquals("August 2023", result)
    }

    @Test
    fun testMonthYearFromDate_NullDate() {
        val date: LocalDate? = null
        val result = calendarUtils.monthYearFromDate(date)
        assertEquals(null, result)
    }

}