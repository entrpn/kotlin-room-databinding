<h1>Sample project using Kotlin, Room, Data Binding, LiveData and ViewModel together.</h1>

<p>This is a sample project showing how to use Android architecture components together and is written in kotlin.</p>

<p>This project uses Room with LiveData, ViewModel and Data Binding. It creates relationships between multiple tables/entities.</p>

<p> This project also uses retrofit, okhttp and glide.</p>

<p>If you get a data binding error when building, you will need to comment out the line "android:onClick="@{() -> viewModel.onItemClick(model)}""
 in people_item.xml, then build and after the build is successful, add the line again and build again. I still haven't figured out why this is happening.</p>

![Demo](Demo.gif)
