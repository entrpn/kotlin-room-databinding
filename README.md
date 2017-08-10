<h1>Sample project using Kotlin, Room, Data Binding, LiveData and ViewModel together.</h1>

<p>This is a sample project showing how to use Android architecture components together and written in Kotlin.</p>

<p>This project uses Room,  LiveData, ViewModel and Data Binding. It also uses retrofit, okhttp and glide.</p>

<h2>Master Branch</h2>

<p> The master branch uses different tables to hold the data coming from retrofit and has relationships between them using foreign keys. When a person is deleted from the People table, the delete cascades to the other tables and deletes all information pertaining to that person.</p>

<h2>One Table Branch</h2>

<p> This is a much simpler architecture. It uses @Embedded annotations to create nested object models inside PeopleModel but holds all the data inside one table.</p>

![Demo](Demo.gif)
