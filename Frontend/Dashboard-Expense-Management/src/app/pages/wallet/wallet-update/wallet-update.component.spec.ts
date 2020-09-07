import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WalletUpdateComponent } from './wallet-update.component';

describe('WalletUpdateComponent', () => {
  let component: WalletUpdateComponent;
  let fixture: ComponentFixture<WalletUpdateComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WalletUpdateComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WalletUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
