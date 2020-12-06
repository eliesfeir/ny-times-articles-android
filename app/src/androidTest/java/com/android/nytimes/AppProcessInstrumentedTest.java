package com.android.nytimes;

import android.view.View;

import com.android.nytimes.activities.MainActivity;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayingAtLeast;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.AllOf.allOf;

/**
 * This class test whole application process
 */
@RunWith(AndroidJUnit4.class)
public class AppProcessInstrumentedTest {

    private static final int SLEEP_TIME = 3000;
    private static final int NB_ITEMS_TO_TRY = 4;
    private static final int SWIPE_REFRESH_PERCENTAGE = 90;
    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<>(MainActivity.class);

    public static ViewAction withCustomConstraints(final ViewAction action, final Matcher<View> constraints) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return constraints;
            }

            @Override
            public String getDescription() {
                return action.getDescription();
            }

            @Override
            public void perform(UiController uiController, View view) {
                action.perform(uiController, view);
            }
        };
    }


    /**
     * @param RecyclerViewId recylerview resource id
     * @return recyclerview items count
     */
    public static int getRecyclerViewItemsCount(@IdRes int RecyclerViewId) {
        final int[] itemsCount = {0};
        Matcher matcher = new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View item) {
                itemsCount[0] = ((RecyclerView) item).getAdapter().getItemCount();
                return true;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
        onView(allOf(withId(RecyclerViewId), isDisplayed())).check(matches(matcher));
        return itemsCount[0];
    }

    /**
     * run application process test
     */
    @Test
    public void runTest() {
        int itemsCount = getRecyclerViewItemsCount(R.id.rvArticles);

        ViewInteraction recyclerview = onView(withId(R.id.rvArticles));

        for (int i = 0; i < itemsCount; i++) {

            if (i + 1 > NB_ITEMS_TO_TRY)
                break;
            recyclerview.perform(RecyclerViewActions.actionOnItemAtPosition(i, click()));

            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            pressBack();

        }

        onView(withId(R.id.srlArticles))
                .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(SWIPE_REFRESH_PERCENTAGE)));

        try {
            Thread.sleep(SLEEP_TIME);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
