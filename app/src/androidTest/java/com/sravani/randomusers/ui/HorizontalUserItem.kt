package com.sravani.randomusers.ui

// Necessary imports
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.*
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sravani.randomusers.data.model.Coordinates
import com.sravani.randomusers.data.model.Location
import com.sravani.randomusers.data.model.Street
import com.sravani.randomusers.data.model.Timezone
import com.sravani.randomusers.ui.base.HorizontalUserItem
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.IsEqual
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HorizontalUserItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun horizontalUserItem_displaysNameAndAddress_andClickTriggersCallback() {
        var clicked = false
        val testName = "John Doe"
        val testImageUrl = "https://example.com/image.jpg"
        val fakeLocation = Location(
            street = Street(123, "Main St"),
            city = "Sample City",
            state = "Sample State",
            country = "Sample Country",
            postcode = "12345",
            coordinates = Coordinates("-7.4297","-86.1175"),
            timezone = Timezone("+9:30", "Adelaide, Darwin")
        )
        composeTestRule.setContent {
            HorizontalUserItem(
                name = testName,
                imageUrl = testImageUrl,
                location = fakeLocation,
                onClick = { clicked = true }
            )
        }
        composeTestRule.onNodeWithText(testName).assertIsDisplayed()

        composeTestRule.onNode(hasClickAction()).performClick()

        composeTestRule.runOnIdle {
            assertThat(clicked, `is`(true))
        }
    }
}
