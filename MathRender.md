

## Learn
#### 常用数学符号的 LaTeX 表示方法
> http://mohu.org/info/symbols/symbols.htm
#### LaTeX大括号用法
http://blog.csdn.net/l740450789/article/details/49487847
#### 如何使上下限出现在求和、积分符号的上下方而不是右边？
> http://blog.sina.com.cn/s/blog_5e16f1770100g7l5.html
#### LaTeX/Mathematics WIKI
> https://en.wikibooks.org/wiki/LaTeX/Mathematics
#### Function Support in KaTex
> https://khan.github.io/KaTeX/function-support.html

## Tools

Latex Math support in VSCode(Markdown+Math):  
https://marketplace.visualstudio.com/items?itemName=goessner.mdmath

Latex Math support in Github(Chrome extension):  
https://chrome.google.com/webstore/detail/github-with-mathjax/ioemnmodlmafdkllaclgeombjnmnbima



## Github will not support math renderring in markdown.

[Github will not support math renderring in markdown.](https://github.com/github/markup/issues/897)  
a good suggetion:
```
I think an officially supported maths plugin in browser would over time would be best solution. The rendering and security problems would be limited to client (think flash updates).
It need not be drab - throw the kitchen sink at it. Allowing some maths input. While the mathjax plugin works for rendering - it falls short in input.

https://chrome.google.com/webstore/detail/github-with-mathjax/ioemnmodlmafdkllaclgeombjnmnbima

screen shot 2017-05-11 at 8 33 41 pm

https://gist.github.com/ivanlukianchuk/e74d0f786c1beae9c0c6
```

## Issues
#### Chrome extension has issue:
> Missing or unrecognized delimiter for \left
https://github.com/orsharir/github-mathjax/issues/16

## Just For Test:  
$f(n)=\Theta(g(n))$  
$$f(n)=\Theta(g(n))$$  

$$
P(n) = \left \{
    \begin{aligned}
    & 1 &                            if \quad n = 1, \\
    & \sum_{k=1}^{n-1}P(k)P(n-k) &   if \quad n \ge 2,
    \end{aligned}
\right.
$$
