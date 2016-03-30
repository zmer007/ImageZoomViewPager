# ImageZoomViewPager
将ViewPager与ImageViewZoom结合起来, 更友好的浏览图片
> ImageViewZoom组件URL：https://github.com/sephiroth74/ImageViewZoom

> 与ViewPager结合时，出现滑动冲突，解决该问题参考：https://gist.github.com/atermenji/3781644

## 修改的地方
- 修改zoomLib的gradle文件，使其可以直接放到Android Studio中
- 修改public boolean canScroll(int direction)方法，解决ViewPager向左滑动的事件冲突
