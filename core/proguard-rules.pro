-keep public class io.ktor.client.** {
    public <methods>;
    private <methods>;
}

-keep class com.google.gson.** { *; }

-keepclassmembers class com.google.gson.** { *; }

-keep class * implements com.google.gson.**

-keep class * extends com.google.gson.**

-keep,allowobfuscation,allowshrinking interface retrofit2.Call

-keep,allowobfuscation,allowshrinking class retrofit2.Response

-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

-keepclassmembers class com.arch.example.network.models.NetworkPagingList {
    public <init>();
}

-dontwarn retrofit2.**

-dontwarn org.codehaus.mojo.**

-keep class retrofit2.** { *; }

-keepattributes Signature

-keepattributes Exceptions

-keepattributes *Annotation*

-keepattributes RuntimeVisibleAnnotations

-keepattributes RuntimeInvisibleAnnotations

-keepattributes RuntimeVisibleParameterAnnotations

-keepattributes RuntimeInvisibleParameterAnnotations

-keepattributes EnclosingMethod

-keepclasseswithmembers class * {
    @retrofit2.* <methods>;
}

-keepclasseswithmembers interface * {
    @retrofit2.* <methods>;
}

-ignorewarnings
-keep class * {
    public private *;
}

-keep class com.google.android.gms.** { *; }

-dontwarn com.google.android.gms.**

-dontwarn org.xmlpull.v1.**

-dontnote org.xmlpull.v1.**

-keep class org.xmlpull.** { *; }

-dontnote retrofit2.Platform

-dontnote retrofit2.Platform$IOS$MainThreadExecutor

-dontwarn retrofit2.Platform$Java8

-keepattributes Signature

-keepattributes Exceptions

-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

-keep class * extends com.raizlabs.android.dbflow.config.DatabaseHolder { *; }

-keep,allowobfuscation @interface com.facebook.common.internal.DoNotStrip

-keep @com.facebook.common.internal.DoNotStrip class *

-keepclassmembers class * {
    @com.facebook.common.internal.DoNotStrip *;
}

-keepclassmembers class * {
    native <methods>;
}

-dontwarn okio.**

-dontwarn com.squareup.okhttp.**

-dontwarn okhttp3.**

-dontwarn javax.annotation.**

-dontwarn com.android.volley.toolbox.**

-dontwarn android.support.v7.**

-keepattributes *Annotation,Signature

-dontwarn com.github.siyamed.**

-keep class com.github.siyamed.shapeimageview.**{ *; }

-dontwarn org.xmlpull.v1.**

-dontwarn uk.co.senab.photoview.**

-keep class androidx.appcompat.widget.** { *; }

-keepattributes SourceFile,LineNumberTable

-keepnames class com.parse.** { *; }

-keepattributes *Annotation*

-keepattributes Signature

-dontwarn com.squareup.**

-dontwarn okio.**

-keepattributes SourceFile,LineNumberTable

-keep class com.parse.*{ *; }

-dontwarn com.parse.**

-dontwarn com.squareup.picasso.**

-keepclasseswithmembernames class * {
    native <methods>;
}