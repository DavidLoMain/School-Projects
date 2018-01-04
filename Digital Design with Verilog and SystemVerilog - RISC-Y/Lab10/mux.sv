`timescale 1ns / 1ns

module mux(IN1, IN2, OUT, SEL);
    parameter SIZE = 8;
    
    input [SIZE - 1:0] IN1;
    input [SIZE - 1:0] IN2;
    input SEL;
    
    output reg [SIZE - 1:0] OUT;
    
    // Although SEL will be either 0 or 1 in this RISC-Y, 
    // Added the last statement so I will know if there's an error instead of defaulting it to one of the options.
    always @(IN1, IN2, SEL)
    begin
        if(SEL)
            OUT <= IN1;
        else if(!SEL)
            OUT <= IN2;
        else
            OUT <= 8'bz;
    end

endmodule
