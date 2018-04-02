# MVPDemo-todo-mvp

简单介绍下，这个todo-mvp的google原版是：

[googlssamples/todo-mvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)

这个Demo演示了谷歌github例子的Model-View-Presenter(MVP)的架构设计，不过说实话，官方的demo写的有点乱，类命名、方法命名等很不清晰。所以自己利用了一上午的时间从头到尾分析阅读了todo-mvp的demo源码，分析梳理流程，总体来说还好，下午的时间就按照这个mvp的架构规范重新写了一个版本的todo-mvp。

原版的todo-mvp用的是sqlite，涉及到了RoomDatabase。让注重mvp阅读人看起来很乱，容易分散重点。所以我这里使用sharedpreferences替换了sqlite的数据增删改查。
整体说来，官方的例子选择的还算容易理解，实现的功能就是列表展示本地存储的任务Task数据。有增加一条、删除一条、获取一条、获取全部任务的功能。

下面的图是我更改后的大概结构：  

![](https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/mvp.png 'mvp')

MVP的整体架构分析：  

![](https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/20180402214545.png 'mvp')  

官方的demo基本上是按照功能分类的，把很多类都混在一起了，我这里的结构整理，相对清晰一些。  

Model里有实体Task，实体操作的相关定义的方法TaskDao，相关定义方法的实现类TaskDaoImpl。

Contract，它的作用就是定义View的接口方法，用于view中回调使用和Presenter的接口方法，用于定义业务操作逻辑，然后将view和presenter关联起来。

Presenter，Presenter的业务真正实现，监听用户UI操作，回调和更新数据给UI。实现了Contract里的presenter，把操作view和操作model关联起来。
例如：
```
    /**
     * 添加数据操作
     */
    @Override
    public void addNewTask(@NonNull Task task) {
        //操作Model
        taskDaoImpl.saveTask(task);
        //操作View
        tasksView.showAddTaskOk(task);
    }
```  
UI层呢，Activity里使用了Fragment，Fragment来实现Constract的view的定义的相关操作view的方法接口。然后在回调的方法里操作数据和布局等等。  





