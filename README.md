# MVPDemo-todo-mvp

简单介绍下，这个todo-mvp的google原版是：

[googlesamples/todo-mvp](https://github.com/googlesamples/android-architecture/tree/todo-mvp/)

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
 
<img src="https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/Screenshot_20180402-213019.png" width="360" height="640" />
<img src="https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/Screenshot_20180402-213029.png" width="360" height="640" />

实现的功能很简单，增删改查，不过展示mvp的这种架构流程还是可以。  

这个比官方版更容易读懂更容易理解，欢迎大家体验学习。  

后续在csdn博客上写一篇mvp的分析使用文章，敬请期待...  
[Android MVP模式介绍和讲解（CSDN博客文章）](https://blog.csdn.net/jay100500/article/details/79829489)  

![](https://github.com/jaychou2012/MVPDemo-todo-mvp/blob/master/20170830212011034.png 'mvp') 



## 《Android开发进阶实战：拓展与提升》已出版


### 新书涵盖Android最新的技术和内容，包括：新布局方式ConstraintLayout 、AndroidX、Jetpack、TV开发等，值得购买阅读。


![Android开发进阶实战：拓展与提升](http://img13.360buyimg.com/n1/jfs/t1/113550/10/7905/112523/5ec79791E6bf5d507/7169944c4d0d6669.jpg "Android开发进阶实战：拓展与提升")


### 纸质书购买：

[京东](https://item.jd.com/69496918930.html "京东")         [天猫](https://detail.tmall.com/item.htm?spm=a220m.1000858.1000725.6.7103434dRkHC8S&id=618745314823&user_id=3446196188&cat_id=2&is_b=1&rn=45bd1618b102199a8f9794a7b8431df4 "天猫")  [当当](http://product.dangdang.com/28552590.html "当当")


