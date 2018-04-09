LOCAL_PATH := $(call my-dir)
include $(CLEAR_VARS)
#OPENCV_INSTALL_MODULES:=on

#添加编译需要的库
include C:\othwe\opencv\opencv-3.4.0-android-sdk\OpenCV-android-sdk\sdk\native\jni\OpenCV.mk


#LOCAL_MODULE := app
LOCAL_C_INCLUDES += $(LOCAL_PATH)
LOCAL_SRC_FILES := native-lib.cpp

#这个是需要引用的库  -l加上库的名字
LOCAL_LDLIBS += -llog -ldl -ljnigraphics

# 生成库的名字   libapp.so
LOCAL_MODULE := app


include $(BUILD_SHARED_LIBRARY)
