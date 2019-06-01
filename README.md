# Retro-Request
<b>Retro Request Library </b>is based on <b>Retrofit Library</b> useful for make the http calls. This library (Retro Request) is<b> easy to use compare to Retrofit</b> just you have to put the parameter. Retrofit is denitely the better alternative to volley in terms of ease of use, performance, extensibility and other things.  It is a <b>type safe </b>REST client for Android built by <b>Square</b>.  Using this tool android developer can make all network stuff much more easier. 

<b>▷ Key Features:</b><br>
✔ Works with any version of app <br>
✔ Upload any type of file (format)<br>
✔ Internet connection check before call<br>
✔ Type Safe<br>
✔ All types of error handled<br>
✔ Toast on No Internet<br>
✔ Snackbar for <b>Retry</b><br>
✔ Log<br>
✔ Light Weight<br>
✔ Url in multi path (5)<br>
✔ Supported Request Method {GET, POST, DELETE, PUT}     Default GET<br>
✔ Tagging for multiple request<br>

## Usage

```
RetroRequest request = new RetroRequest(context, this);
request.setBaseUrl(getBaseUrl());     //set base_url of api  example- "https://github.com/",  "https://github.com:1030/"
request.setPath1("v1.0");             //set path
request.setPath2("swiggy");
request.setPath3("blob");
request.setPath4("master");					// optional
request.setPath5("README.md");					// optional
request.addHeader("Authorization", "JWT eyJhb");		// optional

request.setRequestMethod(RetroRequest.REQUEST_METHOD_GET);   //set the type of api {GET, POST, DELETE, PUT} Default GET
request.setTag(ResponseType.SIGNUP);            //for use multiple request in same activity  set unique tag
request.putQuery("user_id", "1234");            //for put query and body for send data use as key value pair -> &user_id=1234
request.putFile("file1", new File(filepath));   //for send file,   only work with POST and PUT api
//request.setShowRetrySnack(true);              //Default false - for show Retry Snackbar on No Internet Connection.                
//request.setShowToast(true);                   //Default true  - for show Toast on Failure, No Internet                            
request.setShowLog(BuildConfig.DEBUG);          //for show Log of api request and data in build time only
request.execute(true);                          //for execure the request
```
## Gradle
**Step 1.** Add the JitPack repository to your build file
    Add it in your root build.gradle at the end of repositories:

    allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
**Step 2.** Add the dependency
    
    dependencies {
	        implementation 'com.github.1211amarsingh:Retro-Request:Tag'
	}
  
**Step 3.** Add Compile Options in app. gradle

<pre>
compileOptions {
		sourceCompatibility = '1.8'
		targetCompatibility = '1.8'
}
</pre><br>
## Demo
Feel free to clone this project and run in your IDE to see how can be implemented :).

## Version
Latest Version : [![](https://jitpack.io/v/1211amarsingh/retro-request.svg)](https://jitpack.io/#1211amarsingh/retro-request)
## Contributor

<a href="https://www.linkedin.com/in/pramit-chaturvedi-02064147/">Pramit Chaturvedi</a> And Team</b><br>
    
<b> ▷ Other Apps: </b>
* <a href="https://play.google.com/store/apps/details?id=com.kv.swiggyaddresspick">Swiggy Address Pick</a>
* <a href="https://play.google.com/store/apps/details?id=com.kv.callrecorder">Call Recorder Free</a>
* <a href="https://play.google.com/store/apps/details?id=com.kv.popupimageviews1">Popup Image View</a>
          
<b>▷ Library:</b>

- <a href="https://github.com/1211amarsingh/swiggy">Swiggy Address Pick</a>
- <a href="https://github.com/1211amarsingh/PopupImageView">Popup Image View</a>


---------------------------------------------------------
We're always excited to hear from you! If you have any request, suggestions, feedback, questions, or concerns, please email us at:

 <a href="mailto:1211AMARSINGH@GMAIL.COM" >1211AMARSINGH@GMAIL.COM</a>
