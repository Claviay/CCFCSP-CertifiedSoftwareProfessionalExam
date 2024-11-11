#include<bits/stdc++.h>
using namespace std;
void zz(int m2[], int m1[], int p, int q)
{
	int i, j, tt;
	for (tt = 0; tt< p * q; tt++)
	{
		m2[(tt % q) * p + tt/ q] = m1[tt];
	}
	for (tt = 0; tt < p * q; tt++)
	{
		m1[tt] = m2[tt];
	}
}
int main()
{
	int n, m, t, m1[100001], m2[100001],i,j;
	cin >> n >> m>>t;
	int qt = 0;
	for (i = 0; i < n; i++)
	{
		for (j = 0; j < m; j++)
		{
			cin >> m1[qt++];
		}
	}
	int op, a, b;
	int p=n, q=m;
	int flag=0;
	for (i = 1; i <= t; i++)
	{
		cin >> op >> a >> b;
		if (op == 1)
		{
			p = a;
			q = b;
			flag = 1;
			continue;
		}
		if (op == 2)
		{
			zz(m2,m1,p,q);
			int temp;
			temp = p;
			p = q;
			q = temp;
			flag = 2;
		}
		if (op == 3)
		{
			if (flag == 0) cout << m1[a*q+b] << endl;
			if (flag == 1) cout << m1[a * q + b] << endl;
			if (flag == 2) cout << m2[a * q + b] << endl;
		}
	}
}
