# Schedule_optimizer
Team Name: We're Roommates
Member Names: Arthan Bhatt, Mike Beauzile, Katie Hastie
Member Emails: abhatt21@bu.edu, mikebeau@bu.edu knhastie@bu.edu

Our project allows users to input the courses they want to take next semester at bu. 
It then asks for their preferences and our code will optimize their "perfect" class schedule.
It is set out to make student's lives easier come registration time.

To create a project similar to ours, you would need to retrive BU course data. After you need to 
decide how to sort your data to interact with your code. The UI needs to be compatible with your
data types. The optimization code is on a point assignment system based on preferences.

Link to your YouTube video: https://www.youtube.com/watch?v=5XIrQoD8f3g

Challenges and setbacks: 

We had three major challenges that with our application that we could not completely resolve
1. Passing information to the Preferences page
  - We struggled passing the Array list of Strings of Professor names to our Individual course preferences page. Passing data was a huge problem throughout our project. Through constant error checking we had confirmed that we stored data correctly on almost every page but string manipulation in Java and the Android Studio UI was not too friendly. Katie was able to code the entirety of the preferences page with default values and send the data back INTO the Prefeerences main menu page
2. Creating the actual code(though we know braodly how we could potentially do it)
 - This was a very ambitious app with three members to begin with. We understand how we COULD do the optimization code. The courses are already ranked accordingly in the Optimization page. After asking for times vs. Professors, we would have two different schedules where the more preferred option is weighted 3x and the less preffered option is weighted 2x. We would loop through the courses and create all possible schedules and store in a struct. Then we would delete schedules with time conflicts and who the schedule with the highest score. We asked our Mentor Neha and determined that it would require a lot of alogorithms and was out of the scope especially with the time sonctraint and smaller team. So our current code does not have a full optimizatin rather it shows an "optimized" schedule with no time conflicts that still works and saves time for the student.
3. 
