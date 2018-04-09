#APP_STL := gnustl_static
#APP_CPPFLAGS := -frtti -fexceptions

APP_STL := c++_static
APP_CPPFLAGS := -std=c++11 -frtti -fexceptions
APP_ABI := armeabi armeabi-v7a x86
APP_PLATFORM := android-14
NDK_TOOLCHAIN_VERSION := 4.9

APP_BUILD_SCRIPT := Android.mk
