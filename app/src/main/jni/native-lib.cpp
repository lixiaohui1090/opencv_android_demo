#include "com_xuansu_opencv_JniJavaActivity.h"
#include <android/bitmap.h>
#include <opencv2/opencv.hpp>
#include <string>
#include <vector>
using namespace cv;

JNIEXPORT void JNICALL Java_com_xuansu_opencv_JniJavaActivity_getEdge
        (JNIEnv *env, jobject thizz, jobject bitmap) {

    AndroidBitmapInfo info;
    void *pixels;

    CV_Assert(AndroidBitmap_getInfo(env, bitmap, &info) >= 0);
    CV_Assert(info.format == ANDROID_BITMAP_FORMAT_RGBA_8888 ||
              info.format == ANDROID_BITMAP_FORMAT_RGB_565);
    CV_Assert(AndroidBitmap_lockPixels(env, bitmap, &pixels) >= 0);
    CV_Assert(pixels);
    if (info.format == ANDROID_BITMAP_FORMAT_RGBA_8888) {
        Mat temp(info.height, info.width, CV_8UC4, pixels);
        Mat gray;
        cvtColor(temp, gray, COLOR_RGBA2GRAY);
        Canny(gray, gray, 3, 9, 3);
        cvtColor(gray, temp, COLOR_GRAY2RGBA);
    } else {
        Mat temp(info.height, info.width, CV_8UC2, pixels);
        Mat gray;
        cvtColor(temp, gray, COLOR_RGB2GRAY);
        Canny(gray, gray, 3, 9, 3);
        cvtColor(gray, temp, COLOR_GRAY2RGB);
    }
    AndroidBitmap_unlockPixels(env, bitmap);
}

JNIEXPORT jintArray JNICALL Java_com_xuansu_opencv_JniJavaActivity_grayProc
        (JNIEnv * env , jobject jclazz , jintArray buf, jint w , jint h){

    jint *cbuf;

    cbuf=env->GetIntArrayElements(buf, (unsigned char *)false);
    if(cbuf==NULL){
        return 0;
    }
    Mat imgData(h,w,CV_8UC4,(unsigned char *)cbuf);
    uchar* ptr = imgData.ptr(0);
    for (int i = 0; i <w*h ; i++) {
        int grayScale = ptr[4 * i + 2] * 0.299 + ptr[4 * i + 1] * 0.587 + ptr[4 * i + 0] * 0.114;
        ptr[4*i+1]=grayScale;
        ptr[4*i+2]=grayScale;
        ptr[4*i+0]=grayScale;
    }
    int size=w*h;
    jintArray  result=env->NewIntArray(size);
    env->SetIntArrayRegion(result,0,size,cbuf);
    env->ReleaseIntArrayElements(buf,cbuf,0);
    return  result;
}