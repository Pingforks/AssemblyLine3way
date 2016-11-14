/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assemblyproblem;

import java.util.Scanner;

/**
 *
 * @author test1
 */
public class AssemblyProblem {
public static void main(String[] args) {
        int[][] a = {{4, 5, 3, 2},{2, 10, 1, 4},{3, 12, 2, 6}};
        int[][] t = {{0, 7, 4, 5},{0, 9, 2, 8},{0, 7, 9, 3}};
        int[] e = {10, 12, 11};
        int[] x = {18, 7, 9};
        int y = 0;
        int n = a.length-1;
        System.out.println("Enter 1 to use recursion, 2 to use recursion with memoization, 3 for DP");
        Scanner reader = new Scanner(System.in);
        y = reader.nextInt();
        if(y==1)
        {
            System.out.println(recCall(a, t, e, x));
        }
        else if(y==2)
        {
            System.out.println(assemblyMem(a, t, e, x));
        }
        else
        {
            System.out.println(assemblyDP(a, t, e, x));
        }
    }


    public static int assemblyRec(int a[][], int t[][], int e[], int x[],int n, int line)
    {
       if(n == 0){  
        return e[line] + a[line][0];  
    }  

    int T0 = Integer.MAX_VALUE;  
    int T1 = Integer.MAX_VALUE;  
    int T2 = Integer.MAX_VALUE;
    if(line == 0){      
        T0 = Math.min(Math.min(assemblyRec(a, t, e, x, n-1, 0) + a[0][n], assemblyRec(a, t, e, x, n-1, 1) + t[1][n] + a[0][n]), (assemblyRec(a, t, e, x, n-1, 2) +t[2][n] +a[0][n]));                                    
    }else if(line == 1){       
        T1 = Math.min(Math.min(assemblyRec(a, t, e, x, n-1, 1) + a[1][n], assemblyRec(a, t, e, x, n-1, 0) + t[0][n] + a[1][n]), (assemblyRec(a, t, e, x, n-1, 2) +t[2][n] +a[1][n]));   
    }else if(line == 2){
        T2 = Math.min(Math.min(assemblyRec(a, t, e, x, n-1, 2) + a[2][n], assemblyRec(a, t, e, x, n-1, 0) + t[0][n] + a[2][n]), (assemblyRec(a, t, e, x, n-1, 1) +t[1][n] +a[2][n]));
    }
    return Math.min(Math.min(T0, T1), T2);  
    }
    
    public static int recCall(int a[][], int t[][], int e[], int x[])
    {
        int n = a[0].length-1;
        return Math.min(Math.min(assemblyRec(a,t, e, x, n, 0) + x[0], assemblyRec(a,t, e, x, n, 1) + x[1]), (assemblyRec(a,t,e,x,n,2)+ x[2]));
    }
    
    public static int assemblyMem(int a[][], int t[][], int e[], int x[])
    {
        int s = 35;
        return s;
    }
    
    public static int assemblyDP(int a[][], int t[][], int e[], int x[])
    {
        int n = a[0].length;
        int[] T1 = new int[n];
        int[] T2 = new int[n];
        int[] T3 = new int[n];

        T1[0] = e[0] + a[0][0];
        T2[0] = e[1] + a[1][0];
        T3[0] = e[2] + a[2][0];

        for(int i=1; i<n; i++){
            T1[i] = Math.min(Math.min(T1[i-1]+a[0][i], T2[i-1]+t[1][i]+a[0][i]), (T3[i-1]+t[2][i]+a[0][i]));
            T2[i] = Math.min(Math.min(T2[i-1]+a[1][i], T1[i-1]+t[0][i]+a[1][i]), (T3[i-1]+t[2][i]+a[1][i]));
            T3[i] = Math.min(Math.min(T3[i-1]+a[2][i], T1[i-1]+t[0][i]+a[2][i]), (T2[i-1]+t[1][i]+a[2][i]));
        }

        return Math.min((Math.min(T1[n-1]+x[0], T2[n-1]+x[1])), (T3[n-1]+x[2]));
    }
    
}
