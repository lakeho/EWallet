import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WalletUserAddComponent } from './wallet-user-add.component';

describe('WalletUserAddComponent', () => {
  let component: WalletUserAddComponent;
  let fixture: ComponentFixture<WalletUserAddComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WalletUserAddComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WalletUserAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
