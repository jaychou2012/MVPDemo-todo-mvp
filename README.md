# MVPDemo-todo-mvp

简单介绍下，这个todo-mvp的google原版是：

[googlssamples/todo-mvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)

这个Demo演示了谷歌github例子的Model-View-Presenter(MVP)的架构设计，不过说实话，官方的demo写的有点乱，类命名、方法命名等很不清晰。所以自己利用了一上午的时间从头到尾分析阅读了todo-mvp的demo源码，分析梳理流程，总体来说还好，下午的时间就按照这个mvp的架构规范重新写了一个版本的todo-mvp。

原版的todo-mvp用的是sqlite，涉及到了RoomDatabase。让注重mvp阅读人看起来很乱，容易分散重点。所以我这里使用sharedpreferences替换了sqlite的数据增删改查。
整体说来，官方的例子选择的还算容易理解，实现的功能就是列表展示本地存储的任务Task数据。有增加一条、删除一条、获取一条、获取全部任务的功能。

下面的图是我更改后的大概结构：
![](https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/mvp.png 'mvp')


