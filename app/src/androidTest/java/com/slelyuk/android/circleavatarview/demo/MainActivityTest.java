package com.slelyuk.android.circleavatarview.demo;

import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.AppCompatImageView;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

@LargeTest @RunWith(AndroidJUnit4.class) public class MainActivityTest {

  @Rule public ActivityTestRule<MainActivity> mActivityTestRule =
      new ActivityTestRule<>(MainActivity.class);

  private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher,
      final int position) {

    return new TypeSafeMatcher<View>() {
      @Override public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent) && view.equals(
            ((ViewGroup) parent).getChildAt(position));
      }
    };
  }

  @Test public void mainActivityTest() {
    ViewInteraction avatar = onView(allOf(withId(R.id.avatar),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 0), isDisplayed()));
    avatar.check(matches(isDisplayed()));
    avatar.check(matches(isAssignableFrom(AppCompatImageView.class)));

    ViewInteraction avatar2 = onView(allOf(withId(R.id.avatar2),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 1), isDisplayed()));
    avatar2.check(matches(isDisplayed()));
    avatar2.check(matches(isAssignableFrom(AppCompatImageView.class)));

    ViewInteraction avatar3 = onView(allOf(withId(R.id.avatar3),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 3), isDisplayed()));
    avatar3.check(matches(isDisplayed()));
    avatar3.check(matches(isAssignableFrom(AppCompatImageView.class)));

    ViewInteraction avatar4 = onView(allOf(withId(R.id.avatar4),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 2), isDisplayed()));
    avatar4.check(matches(isDisplayed()));
    avatar4.check(matches(isAssignableFrom(AppCompatImageView.class)));

    ViewInteraction avatar5 = onView(allOf(withId(R.id.avatar5),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 4), isDisplayed()));
    avatar5.check(matches(isDisplayed()));
    avatar5.check(matches(isAssignableFrom(AppCompatImageView.class)));

    ViewInteraction avatar6 = onView(allOf(withId(R.id.image6),
        childAtPosition(childAtPosition(withId(android.R.id.content), 0), 5), isDisplayed()));
    avatar6.check(matches(isDisplayed()));
    avatar6.check(matches(isAssignableFrom(AppCompatImageView.class)));
  }
}
