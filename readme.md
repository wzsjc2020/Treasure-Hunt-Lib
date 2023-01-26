# Welcome to use Treasure Hunt Lib

Author:wzsjc2023

Liences:CC4.0

## Why Treasure Hunt Lib

This lib allows you custom a vanilla or mod block into a **custom shoveled block**. You may custom vanilla loot recipes to add more loot item in lootpool by using any tool enchanted with **enchanment.treasurehuntlib.treasure**. You can do further more with Treasure Hunt Lib. i.e., to make a custom shoveled block **acting like vanilla farmland**.

**Important**: You may get start with manual : How to create a shoveled block && How to grow plants on a shoveled block



## Examples

![](img/custom_loot_table.gif)

Example 1: you can find treasure in grass block. when finish excavating block, it will turn this block into a shoveled state. you also can gain extra loots from excavating.

v17.0.2update : allow custom complex loot table

Challenge Level ::star: :star::star:



![](img/random_tick.gif)

Example 2: make custom shoveled blocks acting like farmland

Challenge Level ::star: 



![](img/more_crops.png)

Example 3: grow custom plants/seeds on shoveled blocks 

v17.0.5update : allow plant vanilla Minecraft crops

Challenge Level ::star: 



## How to use

**Important**: the passcode is for visitor.

```
build.gradle
```

```java
repositories {
	maven {
		url "https://maven.pkg.github.com/wzsjc2020/treasure-hunt-lib"
            credentials {
                username = "wzsjc2020"
                password = "
                    part1:ghp_to2r3agUTDs
                    part2:KjAuQZ5avOnkZC
                    part3:bHgko1Dj7ko"
            }
	}
}

dependencies {
	modImplementation ("com.wzssoft:treasurehuntlib:${"1.19-17.1.0"}")
}

combime 3 parts of password into complete password!!!
part1+part2+part3 and no space

```





