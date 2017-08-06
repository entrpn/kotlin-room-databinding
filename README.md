<h1>Sample kotlin room databinding project.</h1>

<p>If you get a data binding error when building, you will need to comment out "android:onClick="@{() -> viewModel.onItemClick(model)}""
 from people_item.xml, build then after the build is successful, add it again and build again. I still haven't figured out why this is happening.</p>

![Demo](Demo.gif)
