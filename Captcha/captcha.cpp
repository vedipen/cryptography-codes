#include<bits/stdc++.h>

#define ll long long
#define pl pair<l,l>
#define pll pair<ll,ll>
#define mp make_pair
#define pb push_back
#define F first
#define S second
#define MOD 1000000007
#define vll vector<ll>

using namespace std;

int main() {
	ll R1=rand() %4 + 6;
  ll R2=rand() % 10;
  string s;
  cout<<"R1 generated is : "<<R1<<endl;
  cout<<"R2 generated is : "<<R2<<endl;
  while(R1--) {
    if(R2<6) {
      ll R3=rand()%10;
      cout<<"R3 generated is : "<<R3<<endl;
      s+=to_string(R3);
    } else {
      ll R3=rand()%26;
      cout<<"R3 generated is : "<<R3<<endl;
      s+=(char)('A'+R3);
    }
    R2=rand() % 10;
    cout<<"R2 generated is : "<<R2<<endl;
  }
  cout<<endl<<"Captcha generated is : "<<s<<endl;
	return 0;
}


//vedipen
