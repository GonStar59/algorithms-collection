function S=bisecc(f,I,TOL)
    a=I(1);
    b=I(2);
    fa=f(a);
    fb=f(b);
    
    if(fa*fb>0) 
        S=[]; fprintf('ERROR'); return; 
    end
    
    N=60;
    S=zeros(1,N);
    n=0;


    while(n<=N && ((b-a)/2)>TOL)

        s=(a+b)/2; fs=f(s);
        n=n+1;
        S(n)=s;

        if fs==0
            break;
        end
        
        if(fa*fs<0) 
            b=s; fb=fs;
        else 
            a=s; fa=fs;
        end
        
        
    end

    S=S(:,1:n);
end