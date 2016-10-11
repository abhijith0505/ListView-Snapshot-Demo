Listview-snapshot
======================

[![Build Status](https://travis-ci.org/abhijith0505/ListView-Snapshot-Demo.svg?branch=master)](https://travis-ci.org/abhijith0505/ListView-Snapshot-Demo)	         &nbsp;&nbsp;	![contributions welcome](https://img.shields.io/badge/contributions-welcome-orange.svg)	         &nbsp;&nbsp;	[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-ListView--Snapshot--Demo-green.svg?style=true)](https://android-arsenal.com/details/1/4208)

A demo android application that uses the listview-snapshot android library.

Use this library to convert any listview into an image (snapshot) and store it anywhere in your phone.

# Usage

You will need to use the **jcenter()** repository in your project level **build.gradle** to use the library with a single line of gradle script.

    allprojects {
	    repositories {
	        jcenter()
	    }
    }
To use the library, add the following dependency to your app level build.gradle file:    	**compile 'com.abhijith:listview-snapshot:1.+'**
    
    dependencies {
		 compile 'com.abhijith:listview-snapshot:1.+'
		...
    }
    
    
Add the permission required in your **Android Manifest**
```
<manifest ...>
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    ...
</manifest>
```

Use the library by making an object of the class **ListViewSnapshot**.

    ListViewSnapshot listViewSnapshot = new ListViewSnapshot(listView, getApplicationContext(), getBaseContext());

Parameters:
- The instance of ListView that has to be saved as a Snapshot.
- Application Context
- Base Context 



To save the listview as a snapshot, use the **convertWholeListViewItemsToSnapshot()** method.

    listViewSnapshot.convertWholeListViewItemsToSnapshot();

The Snapshot is saved to the default location:       
   
    Environment.getExternalStorageDirectory()
                + "/Android/data/"
                + applicationContext.getPackageName()
                + "/Files";

Example:  /sdcard/Android/data/com.abhijith.listview_snapshot_demo/Files
              
 The default name given to the Snapshot is:
      
         "List_"+ timeStamp +".jpg"
         
	    Where the time-stamp is :  ddMMyyy + "_" + Hours + Minutes + Seconds
Example:   List_30072016_123310.jpg


* To provide custom location to store the Snapshot, use the **setPathtoSnapshot(newPath)** method.

        listViewSnapshot.setPathtoSnapshot(newPath);

*  To provide custom name for the Snapshot, use the **renameSnapshot(newName)** method.

        listViewSnapshot.setPathtoSnapshot(newPath);



#Screenshot

![    ](screenshot.jpg?raw=true =100x)


#License

    The MIT License (MIT)

    Copyright (c) 2016 Abhijith C

    Permission is hereby granted, free of charge, to any person obtaining a    copy
    of this software and associated documentation files    (the "Software"), to deal
    in the Software without restriction, including without limitation the     rights
    to use, copy, modify, merge, publish, distribute,      sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR    OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

