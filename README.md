# MinimumBoundingRectangle
Take in a geoJSON polygon &amp; compute the minimum bounding rectangle for that polygon.
This app was developed in Eclipse IDE as a Maven project

## To Run the App
If you are looking to run the app from the command line:

Ensure you have maven properly installed for command line use. You can follow instillation steps here: [Maven Install Page](https://maven.apache.org/install.html)

cd into the MinimumBoundingRectangle folder. Run the following commands:
```
mvn compile
mvn exec:java -Dexec.mainClass=app.Main
```

Personally, I ran the app inside the Eclipse IDE Version: 2021-12 (4.22.0). But any IDE that supports Java/Maven projects should work as well. 

## To Run the Tests
cd into the MinimumBoundingRectangle folder. Run the following commands:
```
mvn test
```
The only currently implemented test is checking that the final rectangle JSONObject contains 5 coordinates. I would like the tests to be more robust. I would need to read more into JUnit tests. I am most familiar with Python unittests so I struggled a bit with writing good tests.

## Enhancement Opportunities
Ideas of what I would implement in the future:
- implement logger
- allow any json file to be read in rather than hard coding the folder location
- also allow user to determine file name when writing to the file
- clean up & optimize runtime
  - determine if the sort methods are the most effective
  - is there any unecessary/reduntant code?
- Figure out why type and coordinates are switched when I write the new JSON to a file
- write more robust tests
  - I included an empty test called 'noCoordsOutOfBounds' which should check that every coordinate in the original json file is within the min & max of both x & y. I started to implement this test but it seemed it would take more time than I could give to it
