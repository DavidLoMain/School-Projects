`timescale 1ns/1ns

module RISCY(CLK, RST, IO);
    input CLK, RST;
    inout [7:0] IO;
    
    // PC input/output wire connections
    wire [4:0] PC2ROM;
    
    // Sequence Controller Output Wires
    wire IR_EN, A_EN, B_EN, PDR_EN, PORT_EN, PORT_RD, PC_EN, LOAD_EN;
    wire ALU_EN, ALU_OE, RAM_OE, RDR_EN, RAM_CS;

    wire IFLAG;
    
    wire AASD2PHASOR_CONTROL; // Wire connecting the AASD to the Phasor generator
    wire [1:0] PHASOR2SEQ; // Wire connecting the Phasor generator to the sequence controller
    wire [31:0] DATA32; // The 32-bit output from the ROM
    wire [7:0] DATA8; // The inout connection from the RAM 4-way connection from diagram in lab manual
    wire [7:0] DATA_MUX; // Data from the ROM/RAM Multiplexer
    wire [6:0] ADDR; 
    wire [7:0] ROM_DATA;
    wire [7:0] RAM_DATA;
    
    wire P_DIR_REG2TRI;
    wire [7:0] P_DATA_REG2TRI;
    
    // Wires from A/B reg to ALU
    wire [7:0] A, B;
    
    // Wires to and from the ALU
    wire [3:0] OPCODE;
	wire OF, SF, ZF, CF;
    
    //     I_ADDR, CLK, PC_EN, LOAD_EN, O_ADDR
    pc pc1(ADDR[4:0], CLK, PC_EN, LOAD_EN, PC2ROM);
    
    // ROM_CS and ROM_OE always left on, so used 1'b1
    ROM rom1(1'b1, 1'b1, PC2ROM, DATA32);
    
    // Memory Instruction Register (CLK, EN, IN, OUT) Changed Input/Output parameter size to 32 bits
    Scalable_Register #(32) mir(CLK, IR_EN, DATA32, {OPCODE, IFLAG, ADDR, ROM_DATA});
    
    // RAM (ADDR input, RAM_OE, WS, RAM_CS, DATA inout)
    RAM ram1(ROM_DATA[4:0], RAM_OE, CLK, RAM_CS, DATA8);
    
    // RAM Data Register (CLK, EN, IN, OUT)
    Scalable_Register ramreg(CLK, RDR_EN, DATA8, RAM_DATA);
    
    // Port Direction Register (CLK, RST_N, EN, Input, Output
    portdirectionregister(CLK, RST, PDR_EN, DATA_MUX[0], P_DIR_REG2TRI);
    
    // Port Data Register CLK, EN, Input, Output
    Scalable_Register pdatareg(CLK, PORT_EN, DATA8, P_DATA_REG2TRI);
    
    // Tri-state buffer 1
    tribuf t1(P_DATA_REG2TRI, P_DIR_REG2TRI, IO);
    // Tri-state buffer 2
    tribuf t2(IO, PORT_RD, DATA8);
    
    // ROM/RAM MUX  (IN1, IN2, OUT, SEL)
    mux m1(ROM_DATA, RAM_DATA, DATA_MUX, RAM_OE);
    
    // A Reg (CLK, EN, IN, OUT) Default size was 8 bits so no need to change parameter
    Scalable_Register areg(CLK, A_EN, DATA_MUX, A);
    
    // B Reg (CLK, EN, IN, OUT) Default size was 8 bits so no need to change parameter
    Scalable_Register breg(CLK, B_EN, DATA_MUX, B);
    
    // ALU (CLK, EN, OE, OPCODE, A, B, OUT, OF, SF, ZF, CF) Default size was 8-bits so no need to change parameter
    ALU alu1(CLK, ALU_EN, ALU_OE, OPCODE, A, B, DATA8, OF, SF, ZF, CF);
    
    // Lab 9 Sequence Controller
    aasd a1(RST, CLK, AASD2PHASOR_CONTROL);
    phasor p1(CLK, AASD2PHASOR_CONTROL, EN, PHASOR2SEQ);
    seq_ctrl sc1(ADDR, OPCODE, PHASOR2SEQ, {OF, SF, ZF, CF}, IFLAG, CLK, IR_EN, A_EN,B_EN, PDR_EN,PORT_EN, PORT_RD, PC_EN, LOAD_EN, ALU_EN, ALU_OE, RAM_OE,RDR_EN, RAM_CS);
    
endmodule

