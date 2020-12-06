# NY Times Most Popular Articles

A basic app that retrieves most popular news from NY times.
It is implemented for showcasing the use of the MVVM (Model - View - ViewModel) Pattern on Android using the LiveData.

## Prerequisites

-Only [Android Studio] is required to be installed. If it's not, please download it from the following link: "https://developer.android.com/studio/"

### Getting Started

First Clone the project from Git repo and then apply the following steps below:
- Sync Gradle
- Rebuild Project

### How to run/install the app

Click on Run button, you should be able to choose between a connected android device or a configured emulator.

### Unit Tests

The app contains two types of automated unit tests:

```
1.Instrumented test

	### Description

	The instrumented test simulate the following:
	- Swipe to refresh action to fetch the news.
	- Click on each item in the list to observe the article content in new activity and then go back to main activity.

	## How to run the instrumented test

	-Find AppProcessInstrumentedTest.java under (androidTest) package.
	-Right click AppProcessInstrumentedTest.java and click Run 'AppProcessInstrumentedTest'.

2.Local test

	### Description

	The local test simulate the following:
	- An API call to fetch the news using retrofit, which its reponse determines the success or the fail of the call.

	### How to run the local test

	-Find NewsRepositoryTest.java under (test) package.
	-Right click NewsRepositoryTest.java and click Run 'NewsRepositoryTest'.
```

### How to generate a coverage report

To generate a coverage report, execute the below command in Terminal window of Android Studio:

```
./gradlew createDebugCoverageReport
```

The report will be generated at the following path:
app/build/outputs/reports/coverage/debug/

## Authors

* **Elie Sfeir**