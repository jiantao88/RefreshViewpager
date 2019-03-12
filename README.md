# RefreshViewpager
通过recycleview实现的可以无限轮播的viewpager
由于使用Viewpager 做无限轮播，刷新图片时，由于Viewpager更新的问题，会出现闪屏的状况，目前解决的办法都不理想，所以选择使用recycleview来实现
Viewpager的效果，但是目前市面上的都无法实现无限轮播，正好谷歌官方出来了一个Viewpager2，其中就是用recycleview实现的Viewpager效果

1.由于直接引入谷歌的Viewpager2 必须引入Androidx库，使得会和现有的项目产生冲突，所以直接把源码引进来
2.解决Viewpager刷新闪屏问题
3.实现无限轮播以及指示器
