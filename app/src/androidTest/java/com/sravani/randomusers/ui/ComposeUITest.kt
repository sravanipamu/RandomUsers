package com.sravani.randomusers.ui

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.runner.RunWith

import org.junit.Assert.*


// Import statements
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.compose.rememberNavController
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import com.google.gson.annotations.SerializedName
import com.sravani.randomusers.data.model.Coordinates
import com.sravani.randomusers.data.model.Dob
import com.sravani.randomusers.data.model.Location
import com.sravani.randomusers.data.model.Name
import com.sravani.randomusers.data.model.Picture
import com.sravani.randomusers.data.model.Street
import com.sravani.randomusers.data.model.Timezone
import com.sravani.randomusers.data.model.User
import com.sravani.randomusers.ui.userlist.HomeRoute
import com.sravani.randomusers.ui.userlist.SearchBox
import com.sravani.randomusers.ui.userlist.UserItemList
import com.sravani.randomusers.utils.AppConstants
import dagger.hilt.android.testing.HiltAndroidTest
import org.hamcrest.core.IsEqual.equalTo
import org.junit.Rule
import org.junit.Test

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeRouteTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBox_emptyInput_showsError() {
        composeTestRule.setContent {
            SearchBox { }
        }
        composeTestRule.onNode(hasSetTextAction()).performImeAction()
        composeTestRule.onNodeWithText("Enter valid number").assertIsDisplayed()
    }

    @Test
    fun searchBox_validInput_callsGoResult() {
        var resultNumber = -1

        composeTestRule.setContent {
            SearchBox { number -> resultNumber = number }
        }
        composeTestRule.onNode(hasSetTextAction()).performTextInput("5")

        composeTestRule.onNode(hasSetTextAction()).performImeAction()

        composeTestRule.runOnIdle {
            assertThat(resultNumber, equalTo(5))
        }
    }

    @Test
    fun userItemList_navigateOnItemClick() {
        val navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        val sampleUsers = listOf(
            User(
                gender = "male",
                name = Name("Mr", "John", "Doe"),
                location = Location(
                    street = Street(123, "Main St"),
                    city = "Sample City",
                    state = "Sample State",
                    country = "Sample Country",
                    postcode = "12345",
                    coordinates = Coordinates("-7.4297","-86.1175"),
                    timezone = Timezone("+9:30", "Adelaide, Darwin")
                ),
                email = "john.doe@example.com",
                picture = Picture(
                    large = "https://example.com/john_large.jpg",
                    medium = "https://example.com/john_medium.jpg",
                    thumbnail = "https://example.com/john_thumb.jpg"
                ),
                nat = "US",
                phone = "123-456-7890",
                dob = Dob("1952-08-14T22:26:03.999Z", 72)
            )
        )

        composeTestRule.setContent {
            UserItemList(userList = sampleUsers, navController = navController)
        }

    }
}
