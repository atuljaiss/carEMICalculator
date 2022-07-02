// IMyAidlInterface.aidl
package com.example.login;

// Declare any non-default types here with import statements

interface IMyAidlInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    double CalculateEMI(double principalAmount, double rate, int tenure);
}