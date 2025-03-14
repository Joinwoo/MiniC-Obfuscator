int main()
{
    int a = 5;
    int b = 10;
    int c[3];
    c[0] = 1;
    c[1] = 2;
    c[2] = 3;
    if(a < b)
    {
        a = a + 1;
    }
    else
    {
        b = b - 1;
    }
    while(a < 10)
    {
        a = a + 1;
        if(a == b)
        {
            return;
        }
    }
    return;
}
int add(int x, int y)
{
    int result;
    result = x + y;
    return result;
}
void printSum()
{
    int sum;
    sum = add(5, 10);
    return;
}