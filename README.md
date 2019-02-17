# Login-Excercise
How to run the test? 
*** Please ensure that *** 
- An andriod emulator installed on your machine
- Android SDK has been downloaded and ANDROID_HOME has been set

For Mac OS
- Start the emulator by opening Terminal and run the commands, 
    - cd $ANDROID_HOME/tools
    - emulator -avd {the_emulator_name}
- Go to the directory that the project is located and running the test by opening another Terminal window and run the commands , 
    - gradle build
    - gradle connectedAndroidTest 
- Get the test result from a generated report, 
    - …/app/build/reports/androidTests/connected/index.html
